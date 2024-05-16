package it.ms.fiscalcodetool.models.response;

import it.ms.fiscalcodetool.models.dto.JsonDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response <T extends JsonDTO>{
    private String message;
    private boolean success;
    private T data;
}
