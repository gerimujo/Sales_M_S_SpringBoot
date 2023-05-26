package com.bar.barproject.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class drinks{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="price")
    private Integer price;
    @Column(name="quantity")
    private Integer quantity;
    
    private String token;
    public String getToken() {
        return token;
    }



    public void setToken(String token) {
        this.token = token;
    }



    public Integer getId() {
        return id;
    }



    public void setId(Integer id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public Integer getPrice() {
        return price;
    }



    public void setPrice(Integer price) {
        this.price = price;
    }



    public Integer getQuantity() {
        return quantity;
    }



    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }






    public drinks(){

    }
    
}
