package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaPagSortRepository;
import com.platzi.pizza.persistence.repository.PizzaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PizzaService {
//    private final JdbcTemplate jdbcTemplate;
    private final PizzaRepository  pizzaRepository;
    private final PizzaPagSortRepository  pizzaPagSortRepository;

    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
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
    public Page<PizzaEntity> getAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    public PizzaEntity get(int idPizza) {
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza){
        return this.pizzaRepository.save(pizza);
    }

    public PizzaEntity findFirstByAvailableTrueAndNameIgnoreCase(String name) {
        return pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name);
    }
    public List<PizzaEntity> getAllNotAvailable() {
        return pizzaRepository.findAllNotAvailable();
    }

    public Page<PizzaEntity> findAllByAvailableTrueOrderByPrice(int page, int elements, String sortBy, String sortDirection) {
        System.out.println(this.pizzaRepository.countAllByVeganTrue());

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);

        return pizzaPagSortRepository.findAllByAvailableTrue(pageRequest);
    }
    public List<PizzaEntity> findAllByAvailableTrueAndNameIgnoreCase(String name) {
        return pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }
    public List<PizzaEntity> getCheapest(double price) {
        return pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
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
