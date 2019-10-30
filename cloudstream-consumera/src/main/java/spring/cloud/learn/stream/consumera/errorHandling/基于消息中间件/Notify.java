package spring.cloud.learn.stream.consumera.errorHandling.基于消息中间件;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.concurrent.atomic.AtomicInteger;

@EnableBinding(Sink3.class)
public class Notify {

    /**
     * 如果不启用DLQ，错误消息会被RabbitMq丢弃
     *
     * @param value
     */
    @StreamListener(Sink3.dropFailedMessage)
    public void dropFailedMessage(String value) {
        System.out.println("dropFailedMessage");
    }




    /**
     * 将错误消息路由到死信队列
     * autoBindDlq: true 会创建DLQ,并将错误信息丢入队列
     * republish-to-dlq: true 请求头中会添加异常信息
     * @param value
     */
    @StreamListener(Sink3.routingToDlq)
    public void routingToDlq(String value) {
        System.out.println("routingToDlq");
    }

    private int requeueTimes = 0;

    @StreamListener(Sink3.requeueRejected)
    public void requeueRejected(Message message) {

        AtomicInteger deliveryAttempt = (AtomicInteger) message.getHeaders().get("deliveryAttempt");

        if (requeueTimes > 3) {
            throw new AmqpRejectAndDontRequeueException("");
        }
        requeueTimes += 1;
        throw new RuntimeException("重新入队");
    }
}
