package org.san.oauth2practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class Oauth2PracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2PracticeApplication.class, args);
    }

}
