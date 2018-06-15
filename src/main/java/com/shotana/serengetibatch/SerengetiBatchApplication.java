package com.shotana.serengetibatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class SerengetiBatchApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SerengetiBatchApplication.class, args);
        App app = context.getBean(App.class);

        try {
            app.run();
        } catch (Throwable throwable) {
            System.out.println("ERROR!!");
            System.out.println(throwable.getClass());
            System.out.println(throwable.getMessage());
        }
    }
}
