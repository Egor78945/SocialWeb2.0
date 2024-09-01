package org.example.authenticationservice.util.builder.friendship;

import com.example.grpc.friendship.FriendshipDatabaseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FriendshipDatabaseServiceBuilderTest {
    @Test
    public void buildLongRequest() {
        FriendshipDatabaseService.LongRequest testRequest = FriendshipDatabaseServiceBuilder.build(1L);
        Assertions.assertEquals(1L, testRequest.getFirstLong());
    }

    @Test
    public void buildLongLongRequest() {
        FriendshipDatabaseService.LongLongRequest testRequest = FriendshipDatabaseServiceBuilder.build(1L, 2L);
        Assertions.assertTrue(testRequest.getFirstLong() == 1L && testRequest.getSecondLong() == 2L);
    }

    @Test
    public void buildLongLongBooleanRequest() {
        FriendshipDatabaseService.LongLongBooleanRequest testRequest = FriendshipDatabaseServiceBuilder.build(1L, 2L, true);
        Assertions.assertTrue(testRequest.getFirstLong() == 1L && testRequest.getSecondLong() == 2L && testRequest.getBoolean());
    }

    @Test
    public void buildLongBooleanRequest() {
        FriendshipDatabaseService.LongBooleanRequest testRequest = FriendshipDatabaseServiceBuilder.build(1L, true);
        Assertions.assertTrue(testRequest.getFirstLong() == 1L && testRequest.getBoolean());
    }
}
