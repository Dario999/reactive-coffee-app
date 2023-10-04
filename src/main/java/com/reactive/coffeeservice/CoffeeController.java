package com.reactive.coffeeservice;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/coffee")
@AllArgsConstructor
public class CoffeeController {

    private final CoffeeService coffeeService;

    @GetMapping
    public Flux<Coffee> all() {
        return coffeeService.getAllCoffees();
    }

    @GetMapping("/{id}")
    public Mono<Coffee> getById(@PathVariable String id) {
        return coffeeService.getCoffeeById(id);
    }

    @GetMapping(value = "/{id}/orders", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CoffeeOrder> orders(@PathVariable String id) {
        return coffeeService.getOrders(id);
    }

}
