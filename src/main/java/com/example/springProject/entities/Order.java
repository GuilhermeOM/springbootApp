package com.example.springProject.entities;

import com.example.springProject.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;
    
    private Integer orderStatus;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;
    
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();
    
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;
    
    public Order(){
    }
    
    public Order(Long id, Instant moment, OrderStatus orderStatus, User client){
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus);
        this.client = client;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }
    
    public void setMoment(Instant moment){
        this.moment = moment;
    }
    public Instant getMoment(){
        return moment;
    }
    
    public void setOrderStatus(OrderStatus orderStatus){
        if(orderStatus != null){
            this.orderStatus = orderStatus.getCode();
        }
    }
    public OrderStatus getOrderStatus(){
        return OrderStatus.valueOf(orderStatus);
    }
    
    public void setClient(User client){
        this.client = client;
    }
    public User getClient(){
        return client;
    }
    
    public void setPayment(Payment payment){
        this.payment = payment;
    }
    public Payment getPayment(){
        return payment;
    }
    
    public Set<OrderItem> getItems(){
        return items;
    }

    public Double getTotal(){
        Double sum = 0.0;
        for(OrderItem x : items){
            sum += x.getSubTotal();
        }
        return sum;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Order other = (Order) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}