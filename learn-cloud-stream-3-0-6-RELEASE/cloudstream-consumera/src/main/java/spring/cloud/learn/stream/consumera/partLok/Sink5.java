package spring.cloud.learn.stream.consumera.partLok;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Sink5 {

    String testPartingLok = "testPartingLok";

    @Input(testPartingLok)
    SubscribableChannel requeueXDeath();
}