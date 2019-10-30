package spring.cloud.learn.stream.consumera.errorHandling.基于消息中间件;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Sink3 {

    String dropFailedMessage = "dorpFailedMessage";
    String routingToDlq = "routingToDlq";
    /**
     * 入队被拒绝的消息
     */
    String requeueRejected =  "requeueRejected";

    @Input(dropFailedMessage)
    SubscribableChannel dropFailedMessage();

    @Input(routingToDlq)
    SubscribableChannel routingToDlq();

    @Input(requeueRejected)
    SubscribableChannel requeueRejected();

}
