package spring.cloud.learn.stream.consumera.errorHandling.Spring应用程序java代码处理重试和errorchannel;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @document https://cloud.spring.io/spring-cloud-static/spring-cloud-stream/2.2.1.RELEASE/spring-cloud-stream.html#_application_error_handling
 */
@EnableBinding({Sink.class,Sink2.class})
@Component
public class CustomErrorHandlers {

    @StreamListener(Sink.INPUT) // destination name 'input.myGroup'
    public void handle(String value) {
        throw new RuntimeException("BOOM!");
    }

    /**
     * 1. input.myGroup抛出的异常会被转发到 input.myGroup.errors 渠道，该渠道是SpringApplication自己的渠道，并没有和消息中间件绑定
     * 2. 如果该方法不抛出异常，则消息会被任务正常处理，不会进入死信队列
     * @param message
     */
    @ServiceActivator(inputChannel = Processor.INPUT + ".myGroup.errors") //channel name 'input.myGroup.errors'
    public void subscribableError(Message<?> message) {
        System.out.println("subscribableError: " + message);
        throw new RuntimeException("处理失败，进入死信");
    }


    /**
     * input2.myGroup2 没有配置对应的 input2.myGroup2.errors 渠道，则异常消息会抛到全局的错误处理渠道 errorChannel
     * @param value
     */
    @StreamListener(Sink2.input2) // destination name 'input.myGroup'
    public void handle2(String value) {
        throw new RuntimeException("BOOM2!");
    }

    /**
     * 全局异常处理渠道。消息一定会进入死信队列
     * @param message
     */
    @StreamListener("errorChannel")
    public void globalError(Message<?> message) {
        System.out.println("全局 errorChannel: ");
    }
}
