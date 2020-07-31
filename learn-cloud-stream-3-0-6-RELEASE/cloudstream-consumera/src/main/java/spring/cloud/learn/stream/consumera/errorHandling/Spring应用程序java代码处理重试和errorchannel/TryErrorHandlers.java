package spring.cloud.learn.stream.consumera.errorHandling.Spring应用程序java代码处理重试和errorchannel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TryErrorHandlers {
    public static void main(String[] args) {
        SpringApplication.run(TryErrorHandlers.class,args);
    }
}
