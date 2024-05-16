package it.ms.fiscalcodetool.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import it.ms.fiscalcodetool.models.entities.BelfioreEntity;

public interface FiscalCodeToolRepository extends JpaRepository<BelfioreEntity, String>{
    
    BelfioreEntity findByProvinceAndMunicipality(String province, String municipality);
    List<BelfioreEntity> findAllByIsItalianMunicipality(String isItalianMunicipality);
    List<BelfioreEntity> findAllByProvinceAndMunicipalityAndIsItalianMunicipality(String province, String municipality, String isItalianMunicipality);
    List<BelfioreEntity> findAllByProvince(String province);
}
