package it.ms.fiscalcodetool.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BELFIORE_CODES", schema = "TOOL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BelfioreEntity {

    @Column(name = "FLAG_COMUNE_NORMAL", length = 1)
    private String isItalianMunicipality;

    @Column(name = "SIGLA_PROVINCIA", length = 2)
    private String province;

    @Column(name = "DESCR_PROV_COMUNE", length = 50)
    private String municipality;

    @Id
    @Column(name = "COMUNE_COD_FISC", length = 4)
    private String belfioreCode;


    public BelfioreEntity(boolean isItalianMunicipality, String province, String municipality, String belfioreCode) {
        setIsItalianMunicipality(isItalianMunicipality ? "S" : "N");
        setProvince(province);
        setMunicipality(municipality);
        setBelfioreCode(belfioreCode);
    }
}