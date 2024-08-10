package org.example.authenticationservice.model.response;

import com.example.grpc.UserDatabaseService;
import lombok.Data;

@Data
public class UserProfile {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String city;
    private int friendCount;
    private String registerDate;
    private String status;

    public UserProfile(UserDatabaseService.GetProfileInformationResponse response){
        this.id = response.getId();
        this.name = response.getName();
        this.surname = response.getSurname();
        this.age = response.getAge();
        this.city = response.getCity();
        this.friendCount = response.getFriendCount();
        this.registerDate = response.getRegisterDate();
        this.status = response.getStatus();
    }

    public UserProfile(Long id, String name, String surname, int age, String city, int friendCount, String registerDate, String status) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.city = city;
        this.friendCount = friendCount;
        this.registerDate = registerDate;
        this.status = status;
    }

    public UserProfile() {
    }
}
