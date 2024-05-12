package it.ms.fiscalcodetool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ms.fiscalcodetool.models.request.GenerateRequest;
import it.ms.fiscalcodetool.models.request.ValidateRequest;
import it.ms.fiscalcodetool.models.response.GenerateResponse;
import it.ms.fiscalcodetool.models.response.Response;
import it.ms.fiscalcodetool.models.response.ValidateResponse;
import it.ms.fiscalcodetool.utils.FiscalCodeGenerator;
import it.ms.fiscalcodetool.utils.FiscalCodeValidator;
import it.ms.fiscalcodetool.utils.ResponseGenerator;


@Service
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
        if (generator.generateFiscalCode(request)) {
            if (generator.generateHomocodianFiscalCodes(generator.getFiscalCode())) {
                return responseGenerator.createSuccessResponse(new GenerateResponse(generator.getFiscalCode(), generator.getHomocodianFiscalCodes()));
            } else {
                return responseGenerator.createErrorResponse(new GenerateResponse(generator.getFiscalCode(), generator.getHomocodianFiscalCodes()), generator.getError());
            }
        } else {
            return responseGenerator.createErrorResponse(new GenerateResponse(generator.getFiscalCode(), generator.getHomocodianFiscalCodes()), generator.getError());
        }
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public Response validate(ValidateRequest request) {        
        if (validator.validateFiscalCode(request.getFiscalCode())) {
            return responseGenerator.createSuccessResponse(new ValidateResponse(validator.isValid(), validator.isHomocodeFiscalCode(), validator.getValidMessage()));
        } else {
            return responseGenerator.createErrorResponse(new ValidateResponse(validator.isValid(), validator.isHomocodeFiscalCode(), validator.getError()), validator.getError());
        }
    }
    
}
