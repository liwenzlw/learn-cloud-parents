package spring.cloud.stream.learn.producera.tryRequiredGroupsAndDlq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TryRequiredGroupsAndDlqSource {

    String tryProducerConfig = "tryProducerConfig";

    @Output(TryRequiredGroupsAndDlqSource.tryProducerConfig)
    MessageChannel tryProducerConfig();
}
