package spring.cloud.learn.stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class FunctionalComposition {

    @Bean
    public Function<String, String> end2() {
        return s -> s + "2";
    }
    @Bean
    public Function<String, String> start1() {
        return s -> "_" + s;
    }
}
