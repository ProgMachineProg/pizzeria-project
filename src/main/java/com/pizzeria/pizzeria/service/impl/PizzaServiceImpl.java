package com.pizzeria.pizzeria.service.impl;

import com.pizzeria.pizzeria.dto.PizzaDto;
import com.pizzeria.pizzeria.models.Pizza;
import com.pizzeria.pizzeria.repository.PizzaRepository;
import com.pizzeria.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

public class PizzaServiceImpl implements PizzaService {
    private PizzaRepository pizzaRepository;
//    @Autowired
    public PizzaServiceImpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }
    @Override
    public List<PizzaDto> findAllPizza() {
        List<Pizza> pizzas = pizzaRepository.findAll();
        return pizzas.stream().map((pizza -> mapToPizzaDto(pizza))).collect(Collectors.toList());
    }

    private PizzaDto mapToPizzaDto(Pizza pizza) {
        PizzaDto pizzaDto = PizzaDto.builder()
                .id(pizza.getId())
                .title(pizza.getTitle())
                .description(pizza.getDescription())
                .price(pizza.getPrice())
                .photoUrl(pizza.getPhotoUrl())
                .build();
        return pizzaDto;
    }
}
