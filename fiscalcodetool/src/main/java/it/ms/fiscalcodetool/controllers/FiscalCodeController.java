package it.ms.fiscalcodetool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.ms.fiscalcodetool.models.request.GenerateRequest;
import it.ms.fiscalcodetool.models.request.ValidateRequest;
import it.ms.fiscalcodetool.models.response.Response;
import it.ms.fiscalcodetool.services.FiscalCodeService;

@RestController
@RequestMapping(path = "/")
public class FiscalCodeController {
    
    @Autowired
    FiscalCodeService service;

    @SuppressWarnings("rawtypes")
    @RequestMapping(path = "/generate")
    public Response generate(@RequestBody GenerateRequest request) {
        return service.generate(request);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(path = "/validate")
    public Response validate(@RequestBody ValidateRequest request) {
        return service.validate(request);
    }

}
