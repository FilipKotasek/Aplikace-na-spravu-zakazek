package Kotasek.Contract.models.dto.mappers;

import Kotasek.Contract.data.entities.ContractEntity;
import Kotasek.Contract.models.dto.ContractDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ContractMapper {
    ContractEntity toEntity(ContractDTO contractDTO);
    ContractDTO toContractDTO(ContractEntity entity);

    void updateContractDTO(ContractDTO source, @MappingTarget ContractDTO target);
}
