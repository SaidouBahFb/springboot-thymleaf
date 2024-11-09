package com.groupeisi.companyspringboot;

import com.groupeisi.companyspringboot.dao.UserRepository;
import com.groupeisi.companyspringboot.enties.RoleEnum;
import com.groupeisi.companyspringboot.enties.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.management.relation.Role;

@SpringBootApplication
public class CompanyspringbootApplication implements CommandLineRunner {
	private final UserRepository userRepository;

    public CompanyspringbootApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(CompanyspringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new UserEntity(1L,"Saidou", "Bah", "saidou@gmail.com", new BCryptPasswordEncoder().encode("passer"), RoleEnum.ADMIN));
		userRepository.save(new UserEntity(2L,"Fama", "Diop", "fama@gmail.com", new BCryptPasswordEncoder().encode("passer"), RoleEnum.USER));
	}
}
