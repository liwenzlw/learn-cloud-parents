package spring.cloud.learn.stream.consumera.测试requireGroupAndDLQ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class 测试requireGroupAndDlqApplication {

    public static void main(String[] args) {
        SpringApplication.run(测试requireGroupAndDlqApplication.class, args);
    }

    @RequestMapping("/hello")
    public String 测试requireGroupAndDlqApplication() {
        return "测试requireGroupAndDlqApplication";
    }
}