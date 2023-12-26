package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;
    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll(){
        return ResponseEntity.ok(this.pizzaService.getAll());
    }
    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> get(@PathVariable int idPizza){
        return ResponseEntity.ok(this.pizzaService.get(idPizza));
    }

    @GetMapping("name/{name}")
    public ResponseEntity<List<PizzaEntity>> findAllByAvailableTrueAndNameIgnoreCase(@PathVariable String name){
        return ResponseEntity.ok(this.pizzaService.findAllByAvailableTrueAndNameIgnoreCase(name));
    }

    @GetMapping("with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWith(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient));
    }
    @GetMapping("without/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWithout(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient));
    }

    @GetMapping("not-available")
    public ResponseEntity<List<PizzaEntity>> getAllNotAvailable(){
        return ResponseEntity.ok(this.pizzaService.getAllNotAvailable());
    }

    @GetMapping("available")
    public ResponseEntity<List<PizzaEntity>> findAllByAvailableTrueOrderByPrice(){
        return ResponseEntity.ok(this.pizzaService.findAllByAvailableTrueOrderByPrice());
    }


    @PostMapping
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizza){
        if (pizza.getIdPizza() == null || !this.pizzaService.exist(pizza.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza){
        if (pizza.getIdPizza() != null && this.pizzaService.exist(pizza.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> deleteEntity(@PathVariable int idPizza){
        if(this.pizzaService.exist(idPizza)){
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
