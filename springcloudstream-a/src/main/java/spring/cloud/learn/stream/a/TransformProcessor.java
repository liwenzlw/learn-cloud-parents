package spring.cloud.learn.stream.a;

import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

public class TransformProcessor {

  @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
  public Object transform(String message) {
    return message.toUpperCase();
  }
}