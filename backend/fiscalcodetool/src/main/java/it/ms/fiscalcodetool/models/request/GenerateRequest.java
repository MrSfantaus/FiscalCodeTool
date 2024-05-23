package it.ms.fiscalcodetool.models.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "GenerateRequest", description = "Generate request")
public class GenerateRequest {
    
    @Schema(description = "name", example = "Mario")
    private String name;

    @Schema(description = "surname", example = "Rossi")
    private String surname;

    @Schema(description = "birth date", example = "2000-01-01")
    private String birthDate;

    @Schema(description = "gender", example = "M")
    private String gender;

    @Schema(description = "italy", example = "true")
    private boolean italy;

    @Schema(description = "province", example = "MI")
    private String province;
    
    @Schema(description = "municipality", example = "MILANO")
    private String municipal;
    
}
