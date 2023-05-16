package com.bar.barproject.Adminhyrreprository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class adminhyr {

    @Id
    @Column(name="id")
    private Integer id;
    @Column(name="user")
    private String user;
    @Column(name="password")
    private String passoword;

    public void id(Integer id){
        this.id =id;
    }
    public void user(String user){
        this.user=user;
    }
    public void passoword(String passowrd){
        this.passoword = passowrd;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPassoword() {
        return passoword;
    }
    public void setPassoword(String passoword) {
        this.passoword = passoword;
    }
    
}
