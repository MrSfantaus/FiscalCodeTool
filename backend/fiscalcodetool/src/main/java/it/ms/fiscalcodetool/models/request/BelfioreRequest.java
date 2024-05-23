package it.ms.fiscalcodetool.models.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "BelfioreRequest", description = "Request object")
public class BelfioreRequest {
    
    @Schema(description = "isItalianMunicipality", example = "true")
    private boolean isItalianMunicipality;

    @Schema(description = "province", example = "MI")
    private String province;

    @Schema(description = "municipality", example = "MILANO")
    private String municipality;
}
