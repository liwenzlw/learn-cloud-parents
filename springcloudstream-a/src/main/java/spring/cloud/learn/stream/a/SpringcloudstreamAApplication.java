package spring.cloud.learn.stream.a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.function.Function;

@SpringBootApplication
@EnableBinding({Processor.class})
public class SpringcloudstreamAApplication {

    public static void main(String[] args) {
       // SpringApplication.run(SpringcloudstreamAApplication.class, args);
        SpringApplication.run(SpringcloudstreamAApplication.class, "--spring.cloud.stream.function.definition=toUpperCase");
    }

    @Bean
    public Function<String, String> toUpperCase() {
        return s -> {
            System.out.println(s);
           return s.toUpperCase();
        };
    }

    @StreamListener(Sink.INPUT)
    public void handle(String person) {
        System.out.println("StreamListener Received: " + person);
    }

    @StreamListener(Sink.INPUT)
    public void handleA(String person) {
        System.out.println("StreamListenerA Received: " + person);
    }

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void handleB(String person) {
        System.out.println("ServiceActivatorB Received: " + person);
    }

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void handleC(String person) {
        System.out.println("ServiceActivatorC Received: " + person);
    }


}
