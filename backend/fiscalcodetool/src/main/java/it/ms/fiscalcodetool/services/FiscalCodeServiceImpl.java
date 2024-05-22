package it.ms.fiscalcodetool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ms.fiscalcodetool.models.request.GenerateRequest;
import it.ms.fiscalcodetool.models.request.ValidateRequest;
import it.ms.fiscalcodetool.models.response.GenerateResponse;
import it.ms.fiscalcodetool.models.response.Response;
import it.ms.fiscalcodetool.models.response.ValidateResponse;
import it.ms.fiscalcodetool.utils.generators.FiscalCodeGenerator;
import it.ms.fiscalcodetool.utils.generators.FiscalCodeValidator;
import it.ms.fiscalcodetool.utils.generators.ResponseGenerator;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class FiscalCodeServiceImpl implements FiscalCodeService{
    
    @Autowired
    FiscalCodeGenerator generator;

    @Autowired
    FiscalCodeValidator validator;

    @Autowired
    private ResponseGenerator responseGenerator;

    @SuppressWarnings("rawtypes")
    @Override
    public Response generate(GenerateRequest request) {
        log.info("Initial Generate request: " + request.toString());
        if (generator.generateFiscalCode(request)) {
            if (generator.generateHomocodianFiscalCodes(generator.getFiscalCode())) {
                log.info("Generate response: OK");
                return responseGenerator.createSuccessResponse(new GenerateResponse(generator.getFiscalCode(), generator.getHomocodianFiscalCodes()));
            } else {
                log.info("Generate response: KO");
                return responseGenerator.createErrorResponse(new GenerateResponse(generator.getFiscalCode(), generator.getHomocodianFiscalCodes()), generator.getError());
            }
        } else {
            log.info("Generate response: KO");
            return responseGenerator.createErrorResponse(new GenerateResponse(generator.getFiscalCode(), generator.getHomocodianFiscalCodes()), generator.getError());
        }
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public Response validate(ValidateRequest request) {        
        log.info("Initial Validate request: " + request.toString());
        if (validator.validateFiscalCode(request.getFiscalCode())) {
            log.info("Validate response: OK");
            return responseGenerator.createSuccessResponse(new ValidateResponse(validator.isValid(), validator.isHomocodeFiscalCode(), validator.getValidMessage()));
        } else {
            log.info("Validate response: KO");
            return responseGenerator.createErrorResponse(new ValidateResponse(validator.isValid(), validator.isHomocodeFiscalCode(), validator.getError()), validator.getError());
        }
    }
    
}
