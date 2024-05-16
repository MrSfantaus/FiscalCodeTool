package it.ms.fiscalcodetool.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BelfioreRequest {
    
    private boolean isItalianMunicipality;
    private String province;
    private String municipality;
}
