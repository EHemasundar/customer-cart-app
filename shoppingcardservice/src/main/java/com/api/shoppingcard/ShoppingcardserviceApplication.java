package com.api.shoppingcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class ShoppingcardserviceApplication {

    public static void main(String[] args) {

        SpringApplication.run(ShoppingcardserviceApplication.class, args);
    }

}
