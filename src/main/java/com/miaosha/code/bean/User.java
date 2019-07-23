package com.miaosha.code.bean;



public class User {
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getSez() {
        return sez;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setSez(String sez) {
        this.sez = sez;
    }

    private String userName;

    private String password;

    private String name;

    private String age;
    private String sez;
}
