package io.cristos.petmanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PetManagementApplication {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(PetManagementApplication.class);

        SpringApplication.run(PetManagementApplication.class, args);

        logger.info("Application startet.");
    }

}
