package com.pizzeria.pizzeria.repository;

import com.pizzeria.pizzeria.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Optional<Pizza> findByTitle(String url);
}
