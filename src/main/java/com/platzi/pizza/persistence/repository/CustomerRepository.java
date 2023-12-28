package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends ListCrudRepository<CustomerEntity, String> {
    @Query(value = "SELECT c.name, c.email FROM CustomerEntity c WHERE c.phoneNumber = :phone")
    List<Object[]> findByPhoneOnlyName(@Param("phone") String phone);
    @Query(value = "SELECT c FROM CustomerEntity c WHERE c.phoneNumber = :phone")
    List<CustomerEntity> findByPhone(@Param("phone") String phone);
}
