package spring.cloud.learn.stream.consumera.测试requireGroupAndDLQ;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@EnableBinding(TryRequiredGroupsInput.class)
@Component
public class TryRequiredGroupsNotification {

    @StreamListener(TryRequiredGroupsInput.tryRequiredGroupsA)
    public void  notifyGroupsA(Message<Object> o) {
        System.out.println("receiver A");
        throw new AmqpRejectAndDontRequeueException("测试死信");
    }

    @StreamListener(TryRequiredGroupsInput.tryRequiredGroupsB)
    public void  notifyGroupsB() {
        throw new RuntimeException("测试死信");
    }
}
