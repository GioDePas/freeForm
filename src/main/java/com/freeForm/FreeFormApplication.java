package com.freeForm;

import com.freeForm.entity.User;
import com.freeForm.enums.Role;
import com.freeForm.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FreeFormApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreeFormApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder ) {
        return args ->
        {
            if (userRepository.findByEmail("giorgio.depascale@outlook.com").isEmpty()) {
                userRepository.save(User.builder()
                        .firstname("Giorgio")
                        .lastname("De Pascale")
                        .email("giorgio.depascale@outlook.com")
                        .password(passwordEncoder.encode("Password-00"))
                        .role(Role.ADMIN)
                        .build()
                );
            }
        };
    }

}
