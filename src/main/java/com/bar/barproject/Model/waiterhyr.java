package com.bar.barproject.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class waiterhyr {

    @Id
    private Integer id;
    private String name;
    private String password;
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


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public waiterhyr(){

    }

    
}
