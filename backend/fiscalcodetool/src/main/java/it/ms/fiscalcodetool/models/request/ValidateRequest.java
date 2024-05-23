package it.ms.fiscalcodetool.models.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ValidateRequest", description = "Validate request")
public class ValidateRequest {
    
    @Schema(description = "Fiscal code", example = "AAAAAA00A00A000A")
    private String fiscalCode;
}
