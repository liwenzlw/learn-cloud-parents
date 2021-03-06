package spring.cloud.learn.stream.consumera.errorHandling.Spring应用程序java代码处理重试和errorchannel;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
public class RetryTemplateErrorHandler {

//    @StreamRetryTemplate
//    public RetryTemplate myRetryTemplate() {
//        return new RetryTemplate();
//    }

    @StreamListener(Sink2.retryTemplate)
    public void retryTemplateHandler(Message msg) {
        Object deliveryAttempt = msg.getHeaders().get("deliveryAttempt");
        System.out.println(deliveryAttempt);
        System.out.println(System.currentTimeMillis());
        throw new RuntimeException("");
    }

}
