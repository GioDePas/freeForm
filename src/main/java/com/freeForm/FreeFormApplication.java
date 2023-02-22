package com.freeForm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FreeFormApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreeFormApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder ) {
        return args -> userRepository.save(User.builder()
                .firstname("Giorgio")
                .lastname("De Pascale")
                .email("giorgio.depascale@outlook.com")
                .password(passwordEncoder.encode("password"))
                .role(Role.ADMIN)
                .build()
        );
    }*/
}
