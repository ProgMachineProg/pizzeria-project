package com.pizzeria.pizzeria.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PizzaDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String photoUrl;
}
