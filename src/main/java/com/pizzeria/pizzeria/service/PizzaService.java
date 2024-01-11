package com.pizzeria.pizzeria.service;

import com.pizzeria.pizzeria.dto.PizzaDto;
import com.pizzeria.pizzeria.models.Pizza;

import java.util.List;

public interface PizzaService {
    List<PizzaDto> findAllPizza();
    Pizza savePizza(Pizza pizza);
}
