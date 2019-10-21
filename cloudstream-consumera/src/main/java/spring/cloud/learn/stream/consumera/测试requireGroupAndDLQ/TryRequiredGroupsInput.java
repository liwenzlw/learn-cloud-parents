package spring.cloud.learn.stream.consumera.测试requireGroupAndDLQ;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface TryRequiredGroupsInput {

    String tryRequiredGroupsA = "tryRequiredGroupsA";

    String tryRequiredGroupsB = "tryRequiredGroupsB";


    @Input(TryRequiredGroupsInput.tryRequiredGroupsA)
    SubscribableChannel tryRequiredGroupsA();


    @Input(TryRequiredGroupsInput.tryRequiredGroupsB)
    SubscribableChannel tryRequiredGroupsB();
}
