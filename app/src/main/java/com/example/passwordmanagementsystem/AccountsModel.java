package com.example.passwordmanagementsystem;

public class AccountsModel {
    private int id;
    private String accountName;
    private String username;
    private String email;
    private String password;
    private String socialNetwork;

    public AccountsModel(){}

    public AccountsModel(int id, String accountName, String username, String email, String password, String socialNetwork) {
        this.id = id;
        this.accountName = accountName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.socialNetwork = socialNetwork;
    }


    @Override
    public String toString() {
        return "AccountsModel{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", socialNetwork='" + socialNetwork + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(String socialNetwork) {
        this.socialNetwork = socialNetwork;
    }
}
