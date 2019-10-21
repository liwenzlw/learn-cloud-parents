package spring.cloud.learn.stream.consumera.测试consumer配置;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface TryConsumerConfig {

    String tryConsumerConfig = "tryConsumerConfig";

    @Input(tryConsumerConfig)
    SubscribableChannel tryConsumerConfig();
}
