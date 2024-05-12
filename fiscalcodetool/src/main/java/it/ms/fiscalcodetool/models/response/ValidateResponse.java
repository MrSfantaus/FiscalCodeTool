package it.ms.fiscalcodetool.models.response;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.ms.fiscalcodetool.models.dto.JsonDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateResponse implements JsonDTO {

    private boolean valid;
    private boolean homocodedFiscalCode;
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
