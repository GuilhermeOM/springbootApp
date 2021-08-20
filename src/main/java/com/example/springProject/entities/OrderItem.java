package com.example.springProject.entities;

import com.example.springProject.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();
    
    private Integer quantity;
    private Double price;
    
    public OrderItem(){
    }
    
    public OrderItem(Order order, Product product, Integer quantity, Double price){
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }
    
    public void setOrder(Order order){
        id.setOrder(order);
    }
    
    @JsonIgnore
    public Order getOrder(){
        return id.getOrder();
    }
    
    public void setProduct(Product product){
        id.setProduct(product);
    }
    public Product getProduct(){
        return id.getProduct();
    }
    
    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }
    public Integer getQuantity(){
        return quantity;
    }
    
    public void setPrice(Double price){
        this.price = price;
    }
    public Double getPrice(){
        return price;
    }
    
    public Double getSubTotal(){
        return price * quantity;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderItem other = (OrderItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
