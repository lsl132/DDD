package org.example.software;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.example")
public class SoftwareApplication {

    public static void main(String[] args) {

        SpringApplication.run(SoftwareApplication.class, args);
    }

}
