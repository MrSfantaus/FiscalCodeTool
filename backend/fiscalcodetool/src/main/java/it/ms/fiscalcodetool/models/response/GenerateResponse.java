package it.ms.fiscalcodetool.models.response;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.ms.fiscalcodetool.models.dto.JsonDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Generate response", example = "{'message': 'OK', 'success': true, 'data':{'fiscalCode': 'FSCLCD37T07H501J', 'homocodesFiscalCode': ['FSCLCD37T07H5L1J', 'FSCLCD37T07H50MJ']}}")
public class GenerateResponse implements JsonDTO {

    private String fiscalCode;
    private List<String> homocodesFiscalCode;

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
