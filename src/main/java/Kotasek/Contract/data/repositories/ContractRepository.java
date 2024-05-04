package Kotasek.Contract.data.repositories;

import Kotasek.Contract.data.entities.ContractEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ContractRepository extends JpaRepository<ContractEntity,Long> {

    @Modifying
    @Transactional
    @Query("update ContractEntity c set c.description=:description,  c.name=:name, c.customer=:customer, c.price=:price  where c.id=:id")
    void updateContractEntity(Long id, String description, String name, String customer, Float price);

}
