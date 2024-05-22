package it.ms.fiscalcodetool.utils.generators;

import org.springframework.stereotype.Component;

import it.ms.fiscalcodetool.models.dto.JsonDTO;
import it.ms.fiscalcodetool.models.response.Response;
import it.ms.fiscalcodetool.utils.Constants;

@Component
public class ResponseGenerator {
        
    public <T extends JsonDTO> Response<T> createSuccessResponse(T responseData) {
        Response<T> response = new Response<>();
        response.setSuccess(true);
        response.setMessage(Constants.OK);
        response.setData(responseData);
        return response;
    }

    public <T extends JsonDTO> Response<T> createErrorResponse(T responseData, String errorMessage) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage("Error occurred: " + errorMessage);
        response.setData(responseData);
        return response;        
    }

}
