package spring.cloud.stream.learn.producera.tryBatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableBinding(TryBatchSource.class)
@RestController
public class TryBatchProducer {

    @Autowired
    @Qualifier("tryBatch")
    private MessageHandler messageHandler;

    @RequestMapping("/tryBatch")
    public String tryBatch (String msg) {
        return msg;
    }
}
