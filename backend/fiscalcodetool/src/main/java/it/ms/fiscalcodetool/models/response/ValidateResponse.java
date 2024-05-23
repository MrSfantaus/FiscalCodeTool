package it.ms.fiscalcodetool.models.response;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.media.Schema;
import it.ms.fiscalcodetool.models.dto.JsonDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Validate response", example = "{'message': 'OK', 'success': true, 'data':{'valid': true, 'homocodedFiscalCode': false, 'description': 'Valid fiscal code'}}")
public class ValidateResponse implements JsonDTO {

    @Schema(description = "Response message", example = "true | false")
    private boolean valid;

    @Schema(description = "Homocoded fiscal code", example = "true | false")
    private boolean homocodedFiscalCode;

    @Schema(description = "Description", example = "Valid fiscal code | Invalid fiscal code")
    private String description;
    
    @Override
    public String toJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {            
            return null;
        }
    }
    
}
