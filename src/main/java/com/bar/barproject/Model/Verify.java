package com.bar.barproject.Model;

public class Verify {
    private String id;
    private String token;


    
    public Verify(String id, String token) {
        this.id = id;
        this.token = token;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    
    
}
