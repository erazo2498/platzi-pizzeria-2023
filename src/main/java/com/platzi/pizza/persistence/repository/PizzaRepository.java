package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity,Integer> {

    @Query("select p from PizzaEntity p where p.available = false")
    List<PizzaEntity> findAllNotAvailable();
}
