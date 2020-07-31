package spring.cloud.learn.stream.consumera.errorHandling.RabbitMQBinder重试;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParkingLot {
    int retryTimes() default 3;
}
