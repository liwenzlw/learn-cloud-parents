package spring.cloud.learn.stream.consumera.errorHandling.RabbitMQBinder重试;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;


@Aspect
@Component
public class PartingLotAware {

    private static final String X_DEATH = "x-death";
    private static final String X_DEATH_QUEUE = "x-first-death-queue";
    private static final String PARTING_LOT_SUFFIX = ".partingLog";
    private static final String PARTING_LOT_EXCHANGE = "ParingLotExchange";
    private static final Exchange exchange = new TopicExchange(PARTING_LOT_EXCHANGE);


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ConnectionFactory connectionFactory;

    private RabbitAdmin rabbitAdmin;

    @Pointcut("@annotation(ParkingLot))")
    private void pointCut() {
    }

    @PostConstruct
    public void init() {
        rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.declareExchange(exchange);
    }
    public PartingLotAware() {
        System.out.println(222);
    }

    @After(value = "pointCut()")
    public void afterReturning(JoinPoint joinPoint ) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if(null == args || args.length<1) {
            throw new IllegalArgumentException("不存在 org.springframework.messaging.Message 参数");
        }
        Optional<Object> first = Arrays.asList(args).stream().filter(p -> p instanceof Message).findFirst();

        try {
            Message msg = (Message)first.get();
            msg.getHeaders().get("");
            String xDeathQueue = (String)msg.getHeaders().get(X_DEATH_QUEUE);
            if(null == xDeathQueue) {
                return;
            }
            ArrayList<HashMap> xDeath = (ArrayList)msg.getHeaders().get(X_DEATH);
            HashMap originQueueXDeath = xDeath.stream().filter(p -> p.get("queue").equals(xDeathQueue)).findFirst().orElseThrow(() -> new IllegalStateException("不存在 x-death 头信息"));
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            ParkingLot parkingLot = methodSignature.getMethod().getAnnotation(ParkingLot.class);
            int i = parkingLot.retryTimes();
            if((long)originQueueXDeath.get("count") >= i) {
                // 进入 parting Lot
                System.out.println("进入 parting Log");
                declareQueueAndBindingPartingLog(xDeathQueue);
               // org.springframework.amqp.core.Message message = new org.springframework.amqp.core.Message();
                //this.rabbitTemplate.send(exchange.getName(),xDeathQueue,msg);

            }
        } catch (Exception e) {
            throw new IllegalArgumentException("不存在 org.springframework.messaging.Message 参数");
        }

        System.out.println(111);
    }

    private String declareQueueAndBindingPartingLog(String originQueue) {
        final String  partingLotQueue = originQueue + PARTING_LOT_SUFFIX;
        Queue queue = new Queue(partingLotQueue);
        rabbitAdmin.declareQueue(queue);
        Binding binding = BindingBuilder.bind(queue).to(exchange).with(originQueue).noargs();
        rabbitAdmin.declareBinding(binding);
        return partingLotQueue;
    }
}
