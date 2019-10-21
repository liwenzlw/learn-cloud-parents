package spring.cloud.stream.learn.producera.tryBatch;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageHandler;

public interface TryBatchSource {

    String tryBatch = "tryBatch";

    @Output(tryBatch)
    MessageHandler tryBatch();
}
