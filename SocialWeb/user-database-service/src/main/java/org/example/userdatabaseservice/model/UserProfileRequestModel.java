package org.example.userdatabaseservice.model;

import lombok.Data;

import java.sql.Date;

@Data
public class UserProfileRequestModel {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String city;
    private int friendCount;
    private Date registerDate;
    private String status;

    public UserProfileRequestModel(Long id, String name, String surname, int age, String city, int friendCount, Date registerDate, String status) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.city = city;
        this.friendCount = friendCount;
        this.registerDate = registerDate;
        this.status = status;
    }

    public UserProfileRequestModel() {
    }
}
