package it.ms.fiscalcodetool.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateRequest {
    
    private String name;
    private String surname;
    private String birthDate;
    private String gender;
    private String italy;
    private String municipal;
    private String province;
}
