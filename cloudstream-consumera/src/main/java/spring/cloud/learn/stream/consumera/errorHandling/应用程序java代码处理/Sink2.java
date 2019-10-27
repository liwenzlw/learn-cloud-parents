package spring.cloud.learn.stream.consumera.errorHandling.应用程序java代码处理;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Sink2 {

    String input2 = "input2";

    @Input(input2)
    SubscribableChannel input2();
}
