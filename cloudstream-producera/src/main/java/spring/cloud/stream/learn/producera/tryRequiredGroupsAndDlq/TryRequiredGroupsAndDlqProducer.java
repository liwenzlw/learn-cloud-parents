package spring.cloud.stream.learn.producera.tryRequiredGroupsAndDlq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@EnableBinding(TryRequiredGroupsAndDlqSource.class)
@RestController
public class TryRequiredGroupsAndDlqProducer {

    @Autowired
    @Qualifier("tryProducerConfig")
    private MessageChannel tryProducerConfig;

    @RequestMapping("/produce")
    public String producer() {
        String s = "produce" + Math.random();
        Map<String,Object> headers = new HashMap<>();
        headers.put("routingKey","tryRequiredGroups");
        headers.put("x-001","001");
        headers.put("x-002","002");
        headers.put("a-002","002");
        headers.put("b-002","002");
        MessageHeaders messageHeaders = new MessageHeaders(headers);
        Message<String> message = MessageBuilder.createMessage(s,messageHeaders);
        tryProducerConfig.send(message);
        System.out.println(s);
        return s;
    }
}
