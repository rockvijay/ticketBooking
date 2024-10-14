package com.trainticket.TrainBooking.Respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trainticket.TrainBooking.Pojo.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	Optional<User> findByConfirmationToken(String confirmationToken);
}
