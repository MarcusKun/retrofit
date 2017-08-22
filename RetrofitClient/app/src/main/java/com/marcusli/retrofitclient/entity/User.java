package com.marcusli.retrofitclient.entity;

/**
 * Created by 59821 on 2017/8/16.
 */

public class User {
    private String username;
    private int password;
    private String address;
    private String message;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "username:" + username + "\n" + "password:" + password + "\n"
                + "address:" + address + "\n" + "message:" + message;
    }
}
