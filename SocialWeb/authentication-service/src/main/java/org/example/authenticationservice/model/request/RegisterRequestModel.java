package org.example.authenticationservice.model.request;

import java.util.Objects;

public class RegisterRequestModel extends AuthenticationRequestModel {
    private String name;
    private String surname;
    private int age;
    private String city;

    public RegisterRequestModel(String name, String surname, int age, String email, String city, String password) {
        super(email, password);
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.city = city;
    }

    public RegisterRequestModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        RegisterRequestModel that = (RegisterRequestModel) object;
        return age == that.age && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(city, that.city) && Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age, getEmail(), city, getPassword());
    }
}
