package spring.cloud.learn.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DelegateToSupplierController {

    /**
     * 需要配置 spring.cloud.stream.definition=streamBridge
     */
    @Autowired
    private StreamBridge streamBridge;


    @RequestMapping("/hello/{body}")
    public void delegateToSupplier(@PathVariable("body") String body) {
        System.out.println("Sending " + body);
        /*
         * 可以发送POJO 或者是 Message 类型，发送效果和Function是一样的，类型转换、分区支持等都是自动完成的
         */
        streamBridge.send("toStream-out-0", body);
        streamBridge.send("order-out-0", body);
        // spring.cloud.stream.source 中没有配置，会在rabbitmq中自动创建
        streamBridge.send("trading-out-0", body);
    }
}
