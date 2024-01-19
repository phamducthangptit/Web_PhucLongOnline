package com.example.PhucLongOnline.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    @JsonProperty("username")
    private String username;
    
    @JsonProperty("password")
    private String password;

    public LoginRequest(){

    }
    
    public LoginRequest(String username,String password){
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswword() {
        return this.password;
    }

    public void setPasswword(String passwword) {
        this.password = passwword;
    }

}
