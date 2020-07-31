package spring.cloud.learn.stream.consumera.errorHandling.Spring应用程序java代码处理重试和errorchannel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Sink2 {

    String input2 = "input2";


    String retryTemplate = "retryTemplate";

    @Input(input2)
    SubscribableChannel input2();

    @Input(retryTemplate)
    SubscribableChannel retryTemplate();
}
