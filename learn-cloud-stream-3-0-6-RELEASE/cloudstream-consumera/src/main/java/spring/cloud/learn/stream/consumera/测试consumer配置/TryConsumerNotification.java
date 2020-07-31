package spring.cloud.learn.stream.consumera.测试consumer配置;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@EnableBinding(TryConsumerConfig.class)
@Component
public class TryConsumerNotification {

    @StreamListener(TryConsumerConfig.tryConsumerConfig)
    public void  notifyTryConsumerConfig(Message<Object> o) throws Exception {
        Thread.sleep(10000L);
        System.out.println("receiver consumer config");
        throw new AmqpRejectAndDontRequeueException("测试死信");
    }
}
