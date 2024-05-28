package it.ms.fiscalcodetool.models.response;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.media.Schema;
import it.ms.fiscalcodetool.models.dto.JsonDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Generate response", example = "{'message': 'OK', 'success': true, 'data':{'fiscalCode': 'FSCLCD37T07H501J', 'homocodesFiscalCode': ['FSCLCD37T07H5L1J', 'FSCLCD37T07H50MJ']}}")
public class GenerateResponse implements JsonDTO {

    @Schema(description = "Fiscal Code", example = "FSCLCD37T07H501J")
    private String fiscalCode;

    @Schema(description = "Homocoded fiscal codes", example = "['FSCLCD37T07H5L1J', 'FSCLCD37T07H50MJ']")
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
