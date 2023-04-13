package com.example.ti.model;

public class SMuser {

    private  String login = "";
    private int authotization = -1;
    // -1 user logged out
    // 1 user logged in
    // 2 administrator

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getAuthotization() {
        return authotization;
    }

    public void setAuthotization(int authotization) {
        this.authotization = authotization;
    }

    @Override
    public String toString(){
        return "SMuser{" +
                "login='" + login + '\'' +
                ", uprawnienia" + authotization +
                '}';

    }
}
