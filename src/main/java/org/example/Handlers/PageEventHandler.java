package org.example.Handlers;

import org.example.Events.PageEvent;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Supplier;

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
}
