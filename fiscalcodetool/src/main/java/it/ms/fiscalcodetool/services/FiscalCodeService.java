package it.ms.fiscalcodetool.services;

import it.ms.fiscalcodetool.models.request.GenerateRequest;
import it.ms.fiscalcodetool.models.request.ValidateRequest;
import it.ms.fiscalcodetool.models.response.Response;

public interface FiscalCodeService {
    
    @SuppressWarnings("rawtypes")
    Response validate(ValidateRequest request);

    @SuppressWarnings("rawtypes")
    Response generate(GenerateRequest request);
}
