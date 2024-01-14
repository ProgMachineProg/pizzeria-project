package com.pizzeria.pizzeria.service;

import com.pizzeria.pizzeria.dto.PizzaDto;
import com.pizzeria.pizzeria.models.Pizza;

import java.util.List;

public interface PizzaService {
    List<PizzaDto> findAllPizza();
    Pizza savePizza(Pizza pizza);

    PizzaDto findPizzaById(long pizzaId);

    void updatePizza(PizzaDto pizza);

    void delete(Long pizzaId);
}
