package com.pizzeria.pizzeria.service.impl;

import com.pizzeria.pizzeria.dto.PizzaDto;
import com.pizzeria.pizzeria.models.Pizza;
import com.pizzeria.pizzeria.repository.PizzaRepository;
import com.pizzeria.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaServiceImpl implements PizzaService {
    private PizzaRepository pizzaRepository;
    @Autowired
    public PizzaServiceImpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }
    @Override
    public List<PizzaDto> findAllPizza() {
        List<Pizza> pizzas = pizzaRepository.findAll();
        return pizzas.stream().map((pizza -> mapToPizzaDto(pizza))).collect(Collectors.toList());
    }

    @Override
    public Pizza savePizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @Override
    public PizzaDto findPizzaById(long pizzaId) {
        Pizza pizza = pizzaRepository.findById(pizzaId).get();
        return mapToPizzaDto(pizza);
    }

    @Override
    public void updatePizza(PizzaDto pizzaDto) {
        Pizza pizza = mapToPizza(pizzaDto);
        pizzaRepository.save(pizza);
    }

    private Pizza mapToPizza(PizzaDto pizza) {
        Pizza pizzaDto = Pizza.builder()
                .id(pizza.getId())
                .title(pizza.getTitle())
                .photoUrl(pizza.getPhotoUrl())
                .description(pizza.getDescription())
                .price(pizza.getPrice())
                .build();
        return pizzaDto;
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
