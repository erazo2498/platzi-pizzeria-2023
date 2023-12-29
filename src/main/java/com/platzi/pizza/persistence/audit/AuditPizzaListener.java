package com.platzi.pizza.persistence.audit;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import jakarta.persistence.*;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {
    private PizzaEntity currentEntity;

    @PostLoad
    public void postLoad(PizzaEntity pizza){
        System.out.println("postLoad Se ejecuta siempre?");
        // HAY PROBLEMAS AL CLONAR
//        this.currentEntity = SerializationUtils.clone(pizzaEntity);
        this.currentEntity = new PizzaEntity(pizza);
        System.out.println("postLoad pizza = " + this.currentEntity);
    }
    @PreUpdate
    @PrePersist
    public void prePersist(PizzaEntity pizzaEntity){
        System.out.println("Pre Persist or Update");
        // HAY PROBLEMAS AL CLONAR
//        this.currentEntity = SerializationUtils.clone(pizzaEntity);
        System.out.println("pre pizzaEntity = " + new PizzaEntity(pizzaEntity));

    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity pizzaEntity){
        System.out.println("Post Persist or Update");
        System.out.println("OLD VALUE = " + (this.currentEntity != null ? this.currentEntity : "Sin informacion"));
        System.out.println("NEW VALUE = " + pizzaEntity.toString());
    }

    @PreRemove
    public  void onPreDelete(PizzaEntity pizzaEntity){
        System.out.println(pizzaEntity.toString());
    }
}
