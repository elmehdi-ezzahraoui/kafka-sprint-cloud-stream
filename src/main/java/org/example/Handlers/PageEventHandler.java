package org.example.Handlers;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.example.Events.PageEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class PageEventHandler {
    @Bean
    public Consumer<PageEvent> handle() {
        return (input) -> {
            System.out.println("Received event: " + input);
            System.out.println("Event name: " + input.name());
        };
    }

    @Bean
    public Supplier<PageEvent> pageEventSupplier() {
        return () -> new PageEvent(Math.random() > 0.5 ? "P1" : "P2", "PageEvent", new java.util.Date(), 10 + new java.util.Random().nextInt(10000));
    }

    @Bean
    public Function<KStream<String, PageEvent>, KStream<String, Long>> kStreamFunction() {
        return (input) ->
                input.filter((k, v) -> v.duration() > 100)
                        .map((k, v) -> new KeyValue<>(v.name(), v.duration()));
    }
}
