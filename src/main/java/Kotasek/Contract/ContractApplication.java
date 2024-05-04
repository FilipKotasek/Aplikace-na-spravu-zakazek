package Kotasek.Contract;

import Kotasek.Contract.data.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ContractApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContractApplication.class, args);
	}

}
