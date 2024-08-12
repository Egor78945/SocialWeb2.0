package org.example.authenticationservice.util.builder;

import com.example.grpc.UserDatabaseService;

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
    public static UserDatabaseService.StringRequest build(String stringValue){
        return UserDatabaseService.StringRequest
                .newBuilder()
                .setString(stringValue)
                .build();
    }
    public static UserDatabaseService.LongRequest build(Long longValue){
        return UserDatabaseService.LongRequest
                .newBuilder()
                .setLong(longValue)
                .build();
    }
}
