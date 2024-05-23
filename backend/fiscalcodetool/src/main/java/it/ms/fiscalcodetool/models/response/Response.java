package it.ms.fiscalcodetool.models.response;

import io.swagger.v3.oas.annotations.media.Schema;
import it.ms.fiscalcodetool.models.dto.JsonDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Response", description = "Response object")
public class Response <T extends JsonDTO>{

    @Schema(description = "Response message")
    private String message;

    @Schema(description = "Response success status")
    private boolean success;

    @Schema(description = "Response data")
    private T data;
}
