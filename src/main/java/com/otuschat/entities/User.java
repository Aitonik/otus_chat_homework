package com.otuschat.entities;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class User {

    private Long id;

    private String password;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String surname;

    private Integer age;

    private String sex;

    private String hobby;

    private String city;

    @NotBlank(message = "Email is mandatory")
    private String email;

    private List<User> friends;

    private List<Role> roles = new ArrayList<>(Arrays.asList(new Role("USER")));
    public User() {}

    public User(Long id, String password, @NotBlank(message = "Name is mandatory") String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public User(Long id, String email, String name, String surname, String age, String sex, String hobby, String city) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = Integer.parseInt(age);
        this.sex = sex;
        this.hobby = hobby;
        this.city = city;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public User setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getHobby() {
        return hobby;
    }

    public User setHobby(String hobby) {
        this.hobby = hobby;
        return this;
    }

    public String getCity() {
        return city;
    }

    public User setCity(String city) {
        this.city = city;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<User> getFriends() {
        return friends;
    }

    public User setFriends(List<User> friends) {
        this.friends = friends;
        return this;
    }
}