package it.ms.fiscalcodetool.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BelfioreDTO {
    
    private boolean isItalianMunicipality;
    private String province;
    private String municipality;
    private String belfioreCode;

    public BelfioreDTO(String isItalianMunicipality, String province, String municipality, String belfioreCode) {
        setItalianMunicipality(isItalianMunicipality.equalsIgnoreCase("S") ? true : false);
        setProvince(province);
        setMunicipality(municipality);
        setBelfioreCode(belfioreCode);
    }

}
