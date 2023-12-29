package com.platzi.pizza.persistence.entity;

import com.platzi.pizza.persistence.audit.AuditPizzaListener;
import com.platzi.pizza.persistence.audit.AuditTableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@Table(name = "PIZZA")
@EntityListeners({AuditingEntityListener.class, AuditPizzaListener.class})
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PizzaEntity extends AuditTableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;
    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(nullable = false, length = 150)
    private String description;

    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double price;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegetarian;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegan;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private Boolean available;

    public PizzaEntity(PizzaEntity pizza) {
        this.idPizza = pizza.getIdPizza();
        this.name = pizza.getName();
        this.description = pizza.getDescription();
        this.price = pizza.getPrice();
        this.vegetarian = pizza.getVegetarian();
        this.vegan = pizza.getVegan();
        this.available = pizza.getAvailable();
    }
}
