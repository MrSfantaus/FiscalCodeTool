package it.ms.fiscalcodetool.models.response;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.ms.fiscalcodetool.models.dto.BelfioreDTO;
import it.ms.fiscalcodetool.models.dto.JsonDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response object containing a list of Belfiore codes.", example = "{'message': 'OK', 'success': true, 'data':{'belfioreCodes': [{'isItalianMunicipality': true, 'province': 'PD', 'municipality': 'ABANO TERME', 'belfioreCode': 'A001'},{'isItalianMunicipality': true, 'province': 'RM', 'municipality': 'ROMA', 'belfioreCode': 'H501'}]}}")
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
