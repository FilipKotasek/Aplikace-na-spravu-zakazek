package Kotasek.Contract.models.services;

import Kotasek.Contract.data.entities.ContractEntity;
import Kotasek.Contract.data.repositories.ContractRepository;
import Kotasek.Contract.models.dto.ContractDTO;
import Kotasek.Contract.models.dto.mappers.ContractMapper;
import Kotasek.Contract.models.exceptions.ContractNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContractService{

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    ContractMapper contractMapper;

    @Override
    public void newContract(ContractDTO contract) {

       ContractEntity newContract=new ContractEntity();

       newContract=contractMapper.toEntity(contract);

        contractRepository.save(newContract);





    }

    @Override
    public List<ContractDTO> GetAll() {

        List<ContractDTO> contacts=new ArrayList<>();

        for (ContractEntity contact:contractRepository.findAll()
             ) {
        contacts.add(contractMapper.toContractDTO(contact));

        }
        return contacts;
    }

    @Override
    public ContractDTO GetId(long id) {

        ContractDTO contract= contractMapper.toContractDTO(contractRepository.findById(id).orElseThrow(ContractNotFoundException::new));

        return contract;
    }

    @Override
    public void updateContract(ContractEntity updatedContract) {

      contractRepository.updateContractEntity(updatedContract.getId(), updatedContract.getDescription(), updatedContract.getName(), updatedContract.getCustomer(), updatedContract.getPrice());

    }

    @Override
    public void remove(Long id) {
        ContractEntity ContractToDelete= contractMapper.toEntity(GetId(id));
        contractRepository.delete(ContractToDelete);

    }


}

