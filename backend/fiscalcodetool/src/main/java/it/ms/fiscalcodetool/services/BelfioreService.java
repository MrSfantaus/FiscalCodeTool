package it.ms.fiscalcodetool.services;

import java.util.List;

import it.ms.fiscalcodetool.models.dto.BelfioreDTO;
import it.ms.fiscalcodetool.models.request.BelfioreRequest;
import it.ms.fiscalcodetool.models.response.Response;

public interface BelfioreService {
    
    List<BelfioreDTO>getAllBelfioreCodes();

    BelfioreDTO getBelfioreCode(String province, String municipality);

    List<BelfioreDTO> getBelfioreCodesByIsItalianMunicipality(String isItalianMunicipality);

    List<BelfioreDTO> getBelfioreCodesByProvince(String province);

    @SuppressWarnings("rawtypes")
    Response getAllBelfioreCodesForJson();

    @SuppressWarnings("rawtypes")
    Response getBelfioreCodeForJson(BelfioreRequest request);

    @SuppressWarnings("rawtypes")
    Response getBelfioreCodesByIsItalianMunicipalityForJson(BelfioreRequest request);

    @SuppressWarnings("rawtypes")
    Response getBelfioreCodesByProvinceForJson(BelfioreRequest request);

}
