package org.example.authenticationservice.util.builder.user;

import com.example.grpc.user.UserDatabaseService;

import java.util.List;

public class UserDatabaseServiceBuilder {
    public static UserDatabaseService.LongStringRequest build(Long longValue, String stringValue) {
        return UserDatabaseService.LongStringRequest
                .newBuilder()
                .setLong(longValue)
                .setString(stringValue)
                .build();
    }

    public static UserDatabaseService.LongIntegerRequest build(Long longValue, Integer integerValue) {
        return UserDatabaseService.LongIntegerRequest
                .newBuilder()
                .setLong(longValue)
                .setInteger(integerValue)
                .build();
    }

    public static UserDatabaseService.StringRequest build(String stringValue) {
        return UserDatabaseService.StringRequest
                .newBuilder()
                .setString(stringValue)
                .build();
    }

    public static UserDatabaseService.LongRequest build(Long longValue) {
        return UserDatabaseService.LongRequest
                .newBuilder()
                .setLong(longValue)
                .build();
    }

    public static UserDatabaseService.LongListRequest build(List<Long> longList){
        return UserDatabaseService.LongListRequest
                .newBuilder()
                .addAllLongs(longList)
                .build();
    }
}
