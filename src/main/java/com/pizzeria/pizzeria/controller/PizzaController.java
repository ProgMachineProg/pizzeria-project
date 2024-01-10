package com.pizzeria.pizzeria.controller;


import com.pizzeria.pizzeria.dto.PizzaDto;
import com.pizzeria.pizzeria.models.Pizza;
import com.pizzeria.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PizzaController {
    private PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/pizzas")
    public String listPizzas(Model model) {
        List<PizzaDto> pizzas = pizzaService.findAllPizza();
        model.addAttribute("pizzas", pizzas);
        return "pizzas-list";
    }

    @GetMapping("/pizzas/new")
    public String createClubForm(Model model) {
        Pizza pizza = new Pizza();
        model.addAttribute("pizza", pizza);
        return "pizzas-create";
    }


}
