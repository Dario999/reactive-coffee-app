package com.reactive.coffeeservice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@Service
@AllArgsConstructor
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    Flux<Coffee> getAllCoffees() {
        return coffeeRepository.findAll();
    }

    Mono<Coffee> getCoffeeById(String id) {
        return coffeeRepository.findById(id);
    }

    Flux<CoffeeOrder> getOrders(String coffeeId) {
        return Flux.<CoffeeOrder>generate(sink -> sink.next(new CoffeeOrder(coffeeId, Instant.now())))
                .delayElements(Duration.ofSeconds(1));
    }
}
