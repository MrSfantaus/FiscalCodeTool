package it.ms.fiscalcodetool.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ms.fiscalcodetool.models.dto.BelfioreDTO;
import it.ms.fiscalcodetool.models.entities.BelfioreEntity;
import it.ms.fiscalcodetool.models.request.BelfioreRequest;
import it.ms.fiscalcodetool.models.response.BelfioreResponse;
import it.ms.fiscalcodetool.models.response.Response;
import it.ms.fiscalcodetool.repositories.FiscalCodeToolRepository;
import it.ms.fiscalcodetool.utils.Constants;
import it.ms.fiscalcodetool.utils.Mapper;
import it.ms.fiscalcodetool.utils.generators.ResponseGenerator;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BelfioreServiceImpl implements BelfioreService{

    @Autowired
    FiscalCodeToolRepository repository;
    
    @Autowired
    Mapper mapper;

    @Autowired
    private ResponseGenerator responseGenerator;

    private String errorString = Constants.KO;    

    @Override
    public List<BelfioreDTO> getAllBelfioreCodes() {
        log.info("Init getAllBelfioreCodes");
        List<BelfioreDTO> list = new ArrayList<BelfioreDTO>();
        try {
            List<BelfioreEntity> entityList = repository.findAll();
            if (entityList != null) {
                list = mapper.mapEntityListToBelfioreDTOList(entityList);
            }
        } catch (Exception e) {
            log.error("Method getAllBelfioreCodes failed: " + e.getMessage());
            setErrorString(errorString + " " + e.getMessage());
        }
        log.info("End getAllBelfioreCodes");
        return list;
    }

    @Override
    public BelfioreDTO getBelfioreCode(String province, String municipality) {
        log.info("Init getBelfioreCode: province = " + province + ", municipality = " + municipality);
        BelfioreDTO dto = new BelfioreDTO();
        try {
            BelfioreEntity entity = repository.findByProvinceAndMunicipality(province, municipality);
            if (entity != null) {
                dto = mapper.mapEntityToBelfioreDTO(entity);
            }
        } catch (Exception e) {
            log.error("Method getBelfioreCode failed: " + e.getMessage());
            setErrorString(errorString + " " + e.getMessage());
        }
        log.info("End getBelfioreCode");
        return dto;
    }

    @Override
    public List<BelfioreDTO> getBelfioreCodesByIsItalianMunicipality(String isItalianMunicipality) {
        log.info("Init getBelfioreCodesByIsItalianMunicipality: isItalianMunicipality = " + isItalianMunicipality);
        List<BelfioreDTO> list = new ArrayList<BelfioreDTO>();
        try {
            List<BelfioreEntity> entityList = repository.findAllByIsItalianMunicipality(isItalianMunicipality);
            if (entityList != null) {
                list = mapper.mapEntityListToBelfioreDTOList(entityList);
            }
        } catch (Exception e) {
            log.error("Method getBelfioreCodesByIsItalianMunicipality failed: " + e.getMessage());
            setErrorString(errorString + " " + e.getMessage());
        }
        log.info("End getBelfioreCodesByIsItalianMunicipality");
        return list;
    }    

    @Override
    public List<BelfioreDTO> getBelfioreCodesByProvince(String province) {
        log.info("Init getBelfioreCodesByProvince: province = " + province);
        List<BelfioreDTO> list = new ArrayList<BelfioreDTO>();
        try {
            List<BelfioreEntity> entityList = repository.findAllByProvince(province);
            if (entityList != null) {
                list = mapper.mapEntityListToBelfioreDTOList(entityList);
            }
        } catch (Exception e) {
            log.error("Method getBelfioreCodesByProvince failed: " + e.getMessage());
            setErrorString(errorString + " " + e.getMessage());
        }
        log.info("End getBelfioreCodesByProvince");
        return list;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Response getAllBelfioreCodesForJson() {
        log.info("Init getAllBelfioreCodesForJson");
        List<BelfioreDTO> codes = getAllBelfioreCodes();
        if (codes == null || codes.isEmpty()) {
            log.info("getAllBelfioreCodesForJson response: KO");
            return responseGenerator.createErrorResponse(new BelfioreResponse(codes), getErrorString());
        } else {
            log.info("getAllBelfioreCodesForJson response: OK");
            return responseGenerator.createSuccessResponse(new BelfioreResponse(codes));
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Response getBelfioreCodeForJson(BelfioreRequest request) {
        log.info("Init getBelfioreCodeForJson: province = " + request.getProvince() + ", municipality = " + request.getMunicipality());
        BelfioreDTO code = getBelfioreCode(request.getProvince(), request.getMunicipality());
        List<BelfioreDTO> codes = new ArrayList<BelfioreDTO>();
        if ( code == null || 
            (code.getBelfioreCode() == null) || code.getBelfioreCode().isEmpty()) {  
            log.info("getBelfioreCodeForJson response: KO");
            return responseGenerator.createErrorResponse(new BelfioreResponse(codes), getErrorString());
        } else {
            codes.add(code);
            log.info("getBelfioreCodeForJson response: OK");
            return responseGenerator.createSuccessResponse(new BelfioreResponse(codes));
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Response getBelfioreCodesByIsItalianMunicipalityForJson(BelfioreRequest request) {
        log.info("Init getBelfioreCodesByIsItalianMunicipalityForJson: isItalianMunicipality = " + request.isItalianMunicipality());
        List<BelfioreDTO> codes = getBelfioreCodesByIsItalianMunicipality(request.isItalianMunicipality() ? "S" : "N");
        if (codes == null || codes.isEmpty()) {
            log.info("getBelfioreCodesByIsItalianMunicipalityForJson response: KO");
            return responseGenerator.createErrorResponse(new BelfioreResponse(codes), getErrorString());
        } else {
            log.info("getBelfioreCodesByIsItalianMunicipalityForJson response: OK");
            return responseGenerator.createSuccessResponse(new BelfioreResponse(codes));
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Response getBelfioreCodesByProvinceForJson(BelfioreRequest request) {
        log.info("Init getBelfioreCodesByProvinceForJson: province = " + request.getProvince());
        List<BelfioreDTO> codes = getBelfioreCodesByProvince(request.getProvince());
        if (codes == null || codes.isEmpty()) {
            log.info("getBelfioreCodesByProvinceForJson response: KO");
            return responseGenerator.createErrorResponse(new BelfioreResponse(codes), getErrorString());
        } else {
            log.info("getBelfioreCodesByProvinceForJson response: OK");
            return responseGenerator.createSuccessResponse(new BelfioreResponse(codes));
        }
    }    

    private void setErrorString(String error) {
        errorString = error;
    }

    private String getErrorString() {
        return errorString;
    }

}
