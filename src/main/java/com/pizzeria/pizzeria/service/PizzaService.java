package com.pizzeria.pizzeria.service;

import com.pizzeria.pizzeria.dto.PizzaDto;

import java.util.List;

public interface PizzaService {
    List<PizzaDto> findAllPizza();
}
