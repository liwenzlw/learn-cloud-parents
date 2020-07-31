package spring.cloud.learn.stream.consumera.errorHandling.RabbitMQBinder重试;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@EnableBinding(Sink4.class)
@Component
public class Notify4 {

    @StreamListener(Sink4.requeueXDeath)
    @ParkingLot
    public void listen( Message msg) {
        System.out.println(System.currentTimeMillis());
        throw new RuntimeException("死信重试");
    }
}
