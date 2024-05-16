package it.ms.fiscalcodetool.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.ms.fiscalcodetool.models.dto.BelfioreDTO;
import it.ms.fiscalcodetool.models.entities.BelfioreEntity;

@Component
public class Mapper {
    
    public BelfioreDTO mapEntityToBelfioreDTO(BelfioreEntity entity) {
        return new BelfioreDTO(entity.getIsItalianMunicipality(), entity.getProvince(), entity.getMunicipality(), entity.getBelfioreCode());
    }

    public List<BelfioreDTO> mapEntityListToBelfioreDTOList(List<BelfioreEntity> entityList) {
        List<BelfioreDTO> list = new ArrayList<BelfioreDTO>();        
        entityList.forEach(entity -> list.add(mapEntityToBelfioreDTO(entity)));        
        return list;
    }

    public BelfioreEntity mapBelfioreDTOToEntity(BelfioreDTO dto) {
        return new BelfioreEntity(dto.isItalianMunicipality(), dto.getProvince(), dto.getMunicipality(), dto.getBelfioreCode());
    }

    public List<BelfioreEntity> mapBelfioreDTOListToEntityList(List<BelfioreDTO> dtoList) {
        List<BelfioreEntity> list = new ArrayList<BelfioreEntity>();        
        dtoList.forEach(dto -> list.add(mapBelfioreDTOToEntity(dto)));        
        return list;
    }
}
