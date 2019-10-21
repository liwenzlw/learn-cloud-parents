package spring.cloud.stream.learn.producera.tryRequiredGroupsAndDlq;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@RestController
public class TryRequiredGroupsAndDlqApplication {

    public static void main(String[] args) {
        SpringApplication.run(TryRequiredGroupsAndDlqApplication.class, args);
    }

}
