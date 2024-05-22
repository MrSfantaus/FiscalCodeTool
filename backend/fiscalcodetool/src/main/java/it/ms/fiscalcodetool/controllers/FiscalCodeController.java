package it.ms.fiscalcodetool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.ms.fiscalcodetool.models.request.BelfioreRequest;
import it.ms.fiscalcodetool.models.request.GenerateRequest;
import it.ms.fiscalcodetool.models.request.ValidateRequest;
import it.ms.fiscalcodetool.models.response.Response;
import it.ms.fiscalcodetool.services.BelfioreService;
import it.ms.fiscalcodetool.services.FiscalCodeService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/")
@Slf4j
public class FiscalCodeController {
    
    @Autowired
    FiscalCodeService fiscalCodeService;

    @Autowired
    BelfioreService belfioreService;

    @SuppressWarnings("rawtypes")
    @PostMapping(path = "/generate")
    public Response generate(@RequestBody GenerateRequest request) {
        log.info("Called API: /generate");
        return fiscalCodeService.generate(request);
    }

    @SuppressWarnings("rawtypes")
    @PostMapping(path = "/validate")
    public Response validate(@RequestBody ValidateRequest request) {
        log.info("Called API: /validate");
        return fiscalCodeService.validate(request);
    }

    @SuppressWarnings("rawtypes")
    @GetMapping(path = "/getAllBelfioreCodes")
    public Response getAllBelfioreCodes() {
        log.info("Called API: /getAllBelfioreCodes");
        return belfioreService.getAllBelfioreCodesForJson();
    }

    @SuppressWarnings("rawtypes")
    @PostMapping(path = "/getBelfioreCode")
    public Response getBelfioreCode(@RequestBody BelfioreRequest request) {
        log.info("Called API: /getBelfioreCode");
        return belfioreService.getBelfioreCodeForJson(request);
    }

    @SuppressWarnings("rawtypes")
    @PostMapping(path = "/getBelfioreCodesByIsItalianMunicipality")
    public Response getBelfioreCodesByIsItalianMunicipality(@RequestBody BelfioreRequest request) {
        log.info("Called API: /getBelfioreCodesByIsItalianMunicipality");
        return belfioreService.getBelfioreCodesByIsItalianMunicipalityForJson(request);
    }

    @SuppressWarnings("rawtypes")
    @PostMapping(path = "/getBelfioreCodesByProvince")
    public Response getBelfioreCodesByProvince(@RequestBody BelfioreRequest request) {
        log.info("Called API: /getBelfioreCodesByProvince");
        return belfioreService.getBelfioreCodesByProvinceForJson(request);
    }
}
