package spring.cloud.learn.stream.consumera.errorHandling.RabbitMQBinder重试;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAspectJAutoProxy
@RestController
public class TryRabbitMqBinderRetryApplication {
    public static void main(String[] args) {
        SpringApplication.run(TryRabbitMqBinderRetryApplication.class,args);
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
