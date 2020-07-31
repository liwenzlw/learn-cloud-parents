package spring.cloud.learn.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.PollableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

@RestController
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    /**
     * 默认生成一个 toUpperCase-in-0 和 一个toUpperCase-out-0 的 exchange 和一个绑定了 toUpperCase-in-0 的匿名队列 toUpperCase-in-0.anonymous.uIJta4wkTr-LVSDg-s_aHQ
     *
     * @return
     */
    @Bean
    public Function<String, String> toUpperCase() {
        return s -> s.toUpperCase();
    }

    @Bean
    public Function<String, String> toLowerCase() {
        return s -> s.toLowerCase();
    }

    /**
     * 默认创建一个 date-out-0 exchange。每当调用它的get（）方法时，前面的bean都会产生一个日期。
     * 框架提供了默认的调用机制，默认是每秒产生一条消息，并且每条消息都发送到到绑定的输出目标中
     *
     * @return
     */
    @Bean
    public Supplier<Date> date() {
        return () -> new Date(12345L);
    }

    /**
     * 默认创建一个 sink-in-0 exchange 和一个绑定的匿名队列 sink-in-0.anonymous.IGCRkdoTTwi34LEE7VXE0A ，并从匿名队列中接收消息。
     * 自定义轮询机制参考：https://cloud.spring.io/spring-cloud-static/spring-cloud-stream/3.0.6.RELEASE/reference/html/spring-cloud-stream.html#_polling_configuration_properties
     * @return
     */
    @Bean
    public Consumer<String> sink() {
        return System.out::println;
    }

    /**
     * 反应是编程模型，与普通Supplier不同，它仅应触发一次，假设调用其get（）方法会产生（提供）连续的消息流，而不是单个消息。
     *
     * @return
     */
    @Bean
    public Supplier<Flux<String>> stringSupplier() {
        return () -> Flux.fromStream(Stream.generate(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(1000);
                    return "Hello from Supplier";
                } catch (Exception e) {
                    // ignore
                }
                return "";
            }
        })).subscribeOn(Schedulers.elastic()).share();
    }

    /**
     * 但是，请设想一下您想轮询某些数据源并返回表示结果集的有限数据流的用例。反应式编程风格是理想机制。但是，鉴于生产流的有限性质，仍需要定期调用此类供应商
     * 使用PollableBean注释（@Bean的子集），从而向框架发出信号，尽管此类提供者的实现是反应性的，但仍需要对其进行轮询。
     * TODO @PollableBean 的使用细节还需要进一步了解
     *
     * @return
     */
    @PollableBean
    public Supplier<Flux<String>> stringPollSupplier() {
        return () -> Flux.just("hello", "bye");
    }

    /**
     * Reactive Consumer有点特殊，因为它的返回类型为空,框架无法再订阅该函数发布的消息。
     * 您极有可能不需要写 Consumer<Flux<?>>，而是用 Function<Flux<?>, Mono<Void>> 取代，并在流的最后调用.then进行处理。
     *
     * 但是，如果确实需要编写显式的Consumer<Flux<?>>，请记住要去订阅传入的Flux
     * @return
     */
    @Bean
    public Function<Flux<String>, Mono<Void>> consumer() {
        return flux -> flux.map(a -> a.substring(1)).filter(a -> a.length() > 1).then();
    }

    @Bean
    public Consumer<Flux<String>> consumers() {
        return flux -> flux.map(a -> a.substring(1)).filter(a -> a.length() > 1).then();
    }
}
