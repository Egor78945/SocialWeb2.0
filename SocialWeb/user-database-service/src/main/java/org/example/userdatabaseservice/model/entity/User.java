package org.example.userdatabaseservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "age")
    private int age;
    @Column(name = "city")
    private String city;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "friend_count")
    private int friendCount;
    @Column(name = "register_date")
    private Date registerDate;
    @Column(name = "status")
    private String status;

    public static Builder newBuilder() {
        return new Builder();
    }

    public User() {
    }

    public User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.age = builder.age;
        this.city = builder.city;
        this.email = builder.email;
        this.password = builder.password;
        this.friendCount = builder.friendCount;
        this.registerDate = builder.registerDate;
        this.status = builder.status;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String surname;
        private int age;
        private String city;
        private String email;
        private String password;
        private int friendCount;
        private Date registerDate;
        private String status;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setFriendCount(int friendCount) {
            this.friendCount = friendCount;
            return this;
        }

        public Builder setRegisterDate() {
            this.registerDate = Date.valueOf(LocalDate.now());
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
