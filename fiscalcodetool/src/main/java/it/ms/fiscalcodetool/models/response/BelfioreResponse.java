package it.ms.fiscalcodetool.models.response;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.ms.fiscalcodetool.models.dto.BelfioreDTO;
import it.ms.fiscalcodetool.models.dto.JsonDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BelfioreResponse implements JsonDTO {

    private List<BelfioreDTO> belfioreCodes;

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
