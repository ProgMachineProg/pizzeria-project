package com.pizzeria.pizzeria.controller;


import com.pizzeria.pizzeria.dto.PizzaDto;
import com.pizzeria.pizzeria.models.Pizza;
import com.pizzeria.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String createPizzaForm(Model model) {
        Pizza pizza = new Pizza();
        model.addAttribute("pizza", pizza);
        return "pizzas-create";
    }

    @PostMapping("/pizzas/new")
    public String savePizza(@ModelAttribute("club") Pizza pizza) {
        pizzaService.savePizza(pizza);
        return "redirect:/pizzas";
    }

    @GetMapping("/pizzas/{pizzaId}/edit")
    public String editPizzaForm(@PathVariable("pizzaId") long pizzaId, Model model) {
        PizzaDto pizza = pizzaService.findPizzaById(pizzaId);
        model.addAttribute("pizza", pizza);
        return "pizza-edit";
    }

    @PostMapping("/pizzas/{pizzaId}/edit")
    public String updatePizza(@PathVariable("pizzaId") Long pizzaId, @ModelAttribute("pizza") PizzaDto pizza) {
        pizza.setId(pizzaId);
        pizzaService.updatePizza(pizza);
        return "redirect:/pizzas";
    }

    @GetMapping("/pizzas/{pizzaId}/delete")
    public String deletePizza(@PathVariable("pizzaId") Long pizzaId) {
        pizzaService.delete(pizzaId);
        return "redirect:/pizzas";
    }



}
