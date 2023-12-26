package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
//    private final JdbcTemplate jdbcTemplate;
    private final PizzaRepository  pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

//    @Autowired
//    public PizzaService(JdbcTemplate jdbcTemplate, PizzaRepository pizzaRepository) {
//        this.jdbcTemplate = jdbcTemplate;
//        this.pizzaRepository = pizzaRepository;
//    }

//    public List<PizzaEntity> getAll() {
//        return this.jdbcTemplate.query("SELECT * FROM PIZZA", new BeanPropertyRowMapper<>(PizzaEntity.class));
//    }
//
//    public List<PizzaEntity> getAllNotAvailable() {
//        return this.jdbcTemplate.query("SELECT * FROM PIZZA WHERE available = 0", new BeanPropertyRowMapper<>(PizzaEntity.class));
//    }
    public List<PizzaEntity> getAll() {
        return this.pizzaRepository.findAll();
    }
    public PizzaEntity get(int idPizza) {
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza){
        return this.pizzaRepository.save(pizza);
    }
    public List<PizzaEntity> getAllNotAvailable() {
        return pizzaRepository.findAllNotAvailable();
    }

    public List<PizzaEntity> findAllByAvailableTrueOrderByPrice() {
        return pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }
    public List<PizzaEntity> findAllByAvailableTrueAndNameIgnoreCase(String name) {
        return pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }
    public List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(description);
    }
    public List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(description);
    }

    public Boolean exist(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }
    public void delete(int idPizza){
        this.pizzaRepository.deleteById(idPizza);
    }

}
