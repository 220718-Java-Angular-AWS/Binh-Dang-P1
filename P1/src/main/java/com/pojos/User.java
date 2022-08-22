package com.pojos;

import java.util.Objects;

public class User {

    private String firstName;
    private String lastName;
    private Integer userId;
    private String username;
    private String email;
    private String password;
    private boolean admin;


    public User(Integer userId, String firstName, String lastName, String username, String email, String password, boolean admin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    public User(String firstName, String lastName, String username, String email, String password, boolean admin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    public User() {

    }

    public boolean isAdmin() {

        return admin;
    }

    public void setAdmin(boolean admin) {

        this.admin = admin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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


    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public String toString() {
        return "{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin + '\'' +
                '}';
    }
}
