package it.ms.fiscalcodetool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.ms.fiscalcodetool.models.request.BelfioreRequest;
import it.ms.fiscalcodetool.models.request.GenerateRequest;
import it.ms.fiscalcodetool.models.request.ValidateRequest;
import it.ms.fiscalcodetool.models.response.Response;
import it.ms.fiscalcodetool.services.BelfioreService;
import it.ms.fiscalcodetool.services.FiscalCodeService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api")
@Slf4j
@Tag(name = "Fiscal Code API", description = "Operations related to Italian Fiscal Codes")
public class FiscalCodeController {
    
    @Autowired
    FiscalCodeService fiscalCodeService;

    @Autowired
    BelfioreService belfioreService;

    @SuppressWarnings("rawtypes")
    @PostMapping(path = "/generate")
    @Operation(summary = "Generate a new fiscal code", description = "Generates a new Italian fiscal code based on the provided data.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fiscal code generated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public Response generate(@RequestBody GenerateRequest request) {
        log.info("Called API: /generate");
        return fiscalCodeService.generate(request);
    }

    @SuppressWarnings("rawtypes")
    @PostMapping(path = "/validate")
    @Operation(summary = "Validate a fiscal code", description = "Validates the provided Italian fiscal code.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fiscal code is valid"),
        @ApiResponse(responseCode = "400", description = "Invalid fiscal code format")
    })
    public Response validate(@RequestBody ValidateRequest request) {
        log.info("Called API: /validate");
        return fiscalCodeService.validate(request);
    }

    @SuppressWarnings("rawtypes")
    @GetMapping(path = "/getAllBelfioreCodes")
    @Operation(summary = "Get all Belfiore codes", description = "Returns a list of all Belfiore codes.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Belfiore codes retrieved successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public Response getAllBelfioreCodes() {
        log.info("Called API: /getAllBelfioreCodes");
        return belfioreService.getAllBelfioreCodesForJson();
    }

    @SuppressWarnings("rawtypes")
    @PostMapping(path = "/getBelfioreCode")
    @Operation(summary = "Get Belfiore code", description = "Returns a Belfiore code based on the provided data.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Belfiore code retrieved successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public Response getBelfioreCode(@RequestBody BelfioreRequest request) {
        log.info("Called API: /getBelfioreCode");
        return belfioreService.getBelfioreCodeForJson(request);
    }

    @SuppressWarnings("rawtypes")
    @PostMapping(path = "/getBelfioreCodesByIsItalianMunicipality")
    @Operation(summary = "Get Belfiore codes by is Italian municipality", description = "Returns a list of Belfiore codes based on the provided data.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Belfiore codes retrieved successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public Response getBelfioreCodesByIsItalianMunicipality(@RequestBody BelfioreRequest request) {
        log.info("Called API: /getBelfioreCodesByIsItalianMunicipality");
        return belfioreService.getBelfioreCodesByIsItalianMunicipalityForJson(request);
    }

    @SuppressWarnings("rawtypes")
    @PostMapping(path = "/getBelfioreCodesByProvince")
    @Operation(summary = "Get Belfiore codes by province", description = "Returns a list of Belfiore codes based on the provided data.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Belfiore codes retrieved successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public Response getBelfioreCodesByProvince(@RequestBody BelfioreRequest request) {
        log.info("Called API: /getBelfioreCodesByProvince");
        return belfioreService.getBelfioreCodesByProvinceForJson(request);
    }
}
