package org.example.friendshipservice.service.friendship.grpc;

import com.example.grpc.friendship.FriendshipDatabaseService;
import com.example.grpc.friendship.FriendshipServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.friendshipservice.service.friendship.FriendshipService;
import org.example.friendshipservice.util.friendship.grpc.FriendshipDatabaseServiceConverter;

@GrpcService
@RequiredArgsConstructor
public class FriendshipGrpcService extends FriendshipServiceGrpc.FriendshipServiceImplBase {
    private final FriendshipService friendshipService;

    @Override
    public void friendshipRequest(FriendshipDatabaseService.LongLongRequest request, StreamObserver<FriendshipDatabaseService.BooleanResponse> responseObserver) {
        responseObserver.onNext(FriendshipDatabaseServiceConverter.convertTo(friendshipService.sendFriendshipRequest(request.getFirstLong(), request.getSecondLong())));
        responseObserver.onCompleted();
    }

    @Override
    public void acceptFriendshipRequest(FriendshipDatabaseService.LongLongRequest request, StreamObserver<FriendshipDatabaseService.BooleanResponse> responseObserver) {
        responseObserver.onNext(FriendshipDatabaseServiceConverter.convertTo(friendshipService.acceptFriendshipRequest(request.getFirstLong(), request.getSecondLong())));
        responseObserver.onCompleted();
    }

    @Override
    public void rejectFriendshipRequest(FriendshipDatabaseService.LongLongRequest request, StreamObserver<FriendshipDatabaseService.BooleanResponse> responseObserver) {
        responseObserver.onNext(FriendshipDatabaseServiceConverter.convertTo(friendshipService.rejectFriendRequest(request.getFirstLong(), request.getSecondLong())));
        responseObserver.onCompleted();
    }

    @Override
    public void discardFriendship(FriendshipDatabaseService.LongLongRequest request, StreamObserver<FriendshipDatabaseService.BooleanResponse> responseObserver) {
        responseObserver.onNext(FriendshipDatabaseServiceConverter.convertTo(friendshipService.discardFriendship(request.getFirstLong(), request.getSecondLong())));
        responseObserver.onCompleted();
    }

    @Override
    public void getAllFriends(FriendshipDatabaseService.LongRequest request, StreamObserver<FriendshipDatabaseService.LongListResponse> responseObserver) {
        responseObserver.onNext(FriendshipDatabaseServiceConverter.convertTo(friendshipService.getAllFriends(request.getFirstLong())));
        responseObserver.onCompleted();
    }

    @Override
    public void getAllFriendshipRequests(FriendshipDatabaseService.LongRequest request, StreamObserver<FriendshipDatabaseService.LongListResponse> responseObserver) {
        responseObserver.onNext(FriendshipDatabaseServiceConverter.convertTo(friendshipService.getAllFriendshipRequests(request.getFirstLong())));
        responseObserver.onCompleted();
    }

    @Override
    public void existsFriendshipByUserIdAndFriendId(FriendshipDatabaseService.LongLongRequest request, StreamObserver<FriendshipDatabaseService.BooleanResponse> responseObserver) {
        responseObserver.onNext(FriendshipDatabaseServiceConverter.convertTo(friendshipService.existsFriendshipByUserIdAndFriendId(request.getFirstLong(), request.getSecondLong())));
        responseObserver.onCompleted();
    }

    @Override
    public void existsFriendshipByUserIdAndFriendIdAndStatus(FriendshipDatabaseService.LongLongBooleanRequest request, StreamObserver<FriendshipDatabaseService.BooleanResponse> responseObserver) {
        responseObserver.onNext(FriendshipDatabaseServiceConverter.convertTo(friendshipService.existsFriendshipByUserIdAndFriendIdAndStatus(request.getFirstLong(), request.getSecondLong(), request.getBoolean())));
        responseObserver.onCompleted();
    }

    @Override
    public void existsFriendshipByUserIdAndStatus(FriendshipDatabaseService.LongBooleanRequest request, StreamObserver<FriendshipDatabaseService.BooleanResponse> responseObserver) {
        responseObserver.onNext(FriendshipDatabaseServiceConverter.convertTo(friendshipService.existsFriendshipByUserIdAndStatus(request.getFirstLong(), request.getBoolean())));
        responseObserver.onCompleted();
    }
}
