package spring.cloud.stream.learn.producera.tryBatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.cloud.stream.learn.producera.tryRequiredGroupsAndDlq.TryRequiredGroupsAndDlqApplication;

@SpringBootApplication
public class TryBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(TryRequiredGroupsAndDlqApplication.class, args);
    }
}
