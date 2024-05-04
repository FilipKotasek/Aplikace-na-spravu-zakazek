package Kotasek.Contract.models.services;

import Kotasek.Contract.data.entities.ContractEntity;
import Kotasek.Contract.models.dto.ContractDTO;

import java.util.List;

public interface ContractService {

void newContract(ContractDTO contract);

List<ContractDTO> GetAll();
ContractDTO GetId(long id);

void updateContract(ContractEntity updatedContract);

void remove(Long id);


}
