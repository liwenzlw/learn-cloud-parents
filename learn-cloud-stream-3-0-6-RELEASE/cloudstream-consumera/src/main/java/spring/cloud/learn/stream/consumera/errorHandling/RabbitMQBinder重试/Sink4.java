package spring.cloud.learn.stream.consumera.errorHandling.RabbitMQBinder重试;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Sink4 {

    String requeueXDeath = "requeueXDeath";

    @Input(requeueXDeath)
    SubscribableChannel requeueXDeath();
}
