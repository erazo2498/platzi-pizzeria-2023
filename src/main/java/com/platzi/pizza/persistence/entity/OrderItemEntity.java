package com.platzi.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ORDER_ITEM")
@Getter
@Setter
@NoArgsConstructor
@IdClass(OrderItemEntity.OrderItemId.class)
public class OrderItemEntity {
    @Id
    @Column(name = "id_item", nullable = false)
    private Integer idItem;

    @Id
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(nullable = false, columnDefinition = "Decimal(2,1)")
    private Double quantity;

    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double price;

    @OneToOne
    @JoinColumn(name = "id_pizza", referencedColumnName = "id_pizza", insertable = false, updatable = false)
    private PizzaEntity pizza;

    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id_order", insertable = false, updatable = false)
    private OrderEntity order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemEntity orderItemEntity)) return false;
        return Objects.equals(idItem, orderItemEntity.idItem) && Objects.equals(idOrder, orderItemEntity.idOrder) && Objects.equals(idPizza, orderItemEntity.idPizza) && Objects.equals(quantity, orderItemEntity.quantity) && Objects.equals(price, orderItemEntity.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idItem, idOrder, idPizza, quantity, price);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemId implements Serializable {
        private Integer idItem;
        private Integer idOrder;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof OrderItemId that)) return false;
            return Objects.equals(idItem, that.idItem) && Objects.equals(idOrder, that.idOrder);
        }

        @Override
        public int hashCode() {
            return Objects.hash(idItem, idOrder);
        }
    }
}
