package com.odk.assistantticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AssistantTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssistantTicketApplication.class, args);
    }

}
