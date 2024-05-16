package it.ms.fiscalcodetool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.ms.fiscalcodetool.models.request.BelfioreRequest;
import it.ms.fiscalcodetool.models.request.GenerateRequest;
import it.ms.fiscalcodetool.models.request.ValidateRequest;
import it.ms.fiscalcodetool.models.response.Response;
import it.ms.fiscalcodetool.services.BelfioreService;
import it.ms.fiscalcodetool.services.FiscalCodeService;

@RestController
@RequestMapping(path = "/")
public class FiscalCodeController {
    
    @Autowired
    FiscalCodeService fiscalCodeService;

    @Autowired
    BelfioreService belfioreService;

    @SuppressWarnings("rawtypes")
    @RequestMapping(path = "/generate")
    public Response generate(@RequestBody GenerateRequest request) {
        return fiscalCodeService.generate(request);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(path = "/validate")
    public Response validate(@RequestBody ValidateRequest request) {
        return fiscalCodeService.validate(request);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(path = "/getAllBelfioreCodes")
    public Response getAllBelfioreCodes() {
        return belfioreService.getAllBelfioreCodesForJson();
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(path = "/getBelfioreCode")
    public Response getBelfioreCode(@RequestBody BelfioreRequest request) {
        return belfioreService.getBelfioreCodeForJson(request);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(path = "/getBelfioreCodesByIsItalianMunicipality")
    public Response getBelfioreCodesByIsItalianMunicipality(@RequestBody BelfioreRequest request) {
        return belfioreService.getBelfioreCodesByIsItalianMunicipalityForJson(request);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(path = "/getBelfioreCodesByProvince")
    public Response getBelfioreCodesByProvince(@RequestBody BelfioreRequest request) {
        return belfioreService.getBelfioreCodesByProvinceForJson(request);
    }
}
