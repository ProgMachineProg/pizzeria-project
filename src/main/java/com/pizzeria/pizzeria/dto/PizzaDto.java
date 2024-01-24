package com.pizzeria.pizzeria.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
