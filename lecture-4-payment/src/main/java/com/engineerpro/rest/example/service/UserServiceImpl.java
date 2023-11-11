package com.engineerpro.rest.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engineerpro.rest.example.dto.GetUserBalanceRequest;
import com.engineerpro.rest.example.dto.GetUserBalanceResponse;
import com.engineerpro.rest.example.exception.UserNotFoundException;
import com.engineerpro.rest.example.model.User;
import com.engineerpro.rest.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  // @Transactional
  // @Override
  // public Payment getUserBalance(int userId, int roomId) {
  // log.info("userid={} start booking", userId);
  // User room = roomRepository.findByIdAndAvailable(roomId, true);
  // if (Objects.isNull(room)) {
  // log.info("No room available with id={}", roomId);
  // throw new RuntimeException(String.format("No room available with id=%d",
  // roomId));
  // }
  // log.info("userid={} selected a room = {}, start saving data", userId, room);
  // Payment booking = Payment.builder().roomId(roomId).userId(userId).build();
  // bookingRepository.save(booking);
  // // delay transaction
  // log.info("start delay");
  // try {
  // TimeUnit.SECONDS.sleep(3);
  // } catch (InterruptedException e) {
  // log.error("error when delay", e);
  // }
  // log.info("finished delay");
  // int updatedRows =
  // roomRepository.updateRoomAsUnavailableWhenPessimisticLocked(roomId);
  // if (updatedRows == 0) {
  // // this case should never happen because row has been lock already
  // log.info("user={} cannot update room, rollback", userId);
  // throw new RuntimeException(String.format("user=%d cannot update room, need
  // rollback", userId));
  // }
  // log.info("saved updated={}", updatedRows);
  // log.info("user={} succesfully booked room={}", userId, roomId);
  // return booking;
  // }

  @Override
  public GetUserBalanceResponse getUserBalance(GetUserBalanceRequest request) {
    final User user = userRepository.findById(request.getUserId()).orElseThrow(UserNotFoundException::new);
    return GetUserBalanceResponse.builder().balance(user.getBalance()).build();
  }

}
