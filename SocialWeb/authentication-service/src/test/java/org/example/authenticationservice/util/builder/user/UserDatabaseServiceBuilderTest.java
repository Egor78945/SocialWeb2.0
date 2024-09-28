package org.example.authenticationservice.util.builder.user;

import com.example.grpc.user.UserDatabaseService;
import org.example.authenticationservice.util.user.builder.grpc.UserDatabaseServiceBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserDatabaseServiceBuilderTest {
    @Test
    public void buildLongStringRequest() {
        UserDatabaseService.LongStringRequest testRequest = UserDatabaseServiceBuilder.build(1L, "abc");
        Assertions.assertTrue(testRequest.getLong() == 1L && testRequest.getString().equals("abc"));
    }

    @Test
    public void buildLongIntegerRequest() {
        UserDatabaseService.LongIntegerRequest testRequest = UserDatabaseServiceBuilder.build(1L, 1);
        Assertions.assertTrue(testRequest.getLong() == 1L && testRequest.getInteger() == 1);
    }

    @Test
    public void buildStringRequest() {
        UserDatabaseService.StringRequest testRequest = UserDatabaseServiceBuilder.build("abc");
        Assertions.assertEquals("abc", testRequest.getString());
    }

    @Test
    public void buildLongRequest() {
        UserDatabaseService.LongRequest testRequest = UserDatabaseServiceBuilder.build(1L);
        Assertions.assertEquals(1L, testRequest.getLong());
    }

    @Test
    public void buildLongListRequest() {
        List<Long> testList = List.of(1L,2L,3L);
        UserDatabaseService.LongListRequest testRequest = UserDatabaseServiceBuilder.build(testList);
        Assertions.assertEquals(testRequest.getLongsList(), testList);
    }
}
