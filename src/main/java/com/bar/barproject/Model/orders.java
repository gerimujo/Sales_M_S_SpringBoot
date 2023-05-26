package com.bar.barproject.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class orders {


@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name="id")
    private Integer id;
    @Column(name="iduser")
    private Integer iduser;
    @Column(name="name")
    private String name;
    @Column(name="product")
    private String product;
    @Column(name="quatity")
    private Integer quatity;
    @Column(name="price")
    private Integer price;
    @Column(name="idper")
    private Integer idper;
    @Column(name="day")
    private Integer day;
    @Column(name="dayaktiv")
    private Integer dayaktiv;


    private String token;
    public String getToken() {
        return token;
    }



    public void setToken(String token) {
        this.token = token;
    }
public orders(){

}

public Integer getPrice() {
    return price;
}
public void setPrice(Integer price) {
    this.price = price;
}
public Integer getId() {
    return id;
}
public void setId(Integer id) {
    this.id = id;
}
public Integer getIduser() {
    return iduser;
}
public void setIduser(Integer iduser) {
    this.iduser = iduser;
}
public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}
public String getProduct() {
    return product;
}
public void setProduct(String product) {
    this.product = product;
}
public Integer getQuatity() {
    return quatity;
}
public void setQuatity(Integer quatity) {
    this.quatity = quatity;
}
public Integer getDay() {
    return day;
}
public void setDay(Integer day) {
    this.day = day;
}
public Integer getDayaktiv() {
    return dayaktiv;
}
public void setDayaktiv(Integer dayaktiv) {
    this.dayaktiv = dayaktiv;
}

public Integer getIdper() {
    return idper;
}

public void setIdper(Integer idper) {
    this.idper = idper;
}


    
}
