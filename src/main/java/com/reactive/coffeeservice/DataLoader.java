package com.reactive.coffeeservice;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
public class DataLoader {

    private final CoffeeRepository repository;

    DataLoader(CoffeeRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    private void load() {
        repository.deleteAll().thenMany(
                        Flux.just("Americano", "Espreso", "Kaldi's Coffee", "Delta", "Java")
                                .map(name -> new Coffee(UUID.randomUUID().toString(), name))
                                .flatMap(repository::save))
                .thenMany(repository.findAll())
                .subscribe(System.out::println);
    }
}
