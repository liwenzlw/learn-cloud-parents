package spring.cloud.learn.consumerb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(Sink.class)
public class CloudstreamConsumerbApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudstreamConsumerbApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello consumerb";
    }

    @StreamListener(Sink.INPUT)
    public void consumer(Message<String> msg) {
        System.out.println(msg);
    }
}
