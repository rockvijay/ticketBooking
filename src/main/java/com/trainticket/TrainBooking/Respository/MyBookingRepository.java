package com.trainticket.TrainBooking.Respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trainticket.TrainBooking.Pojo.Book;

@Repository
public interface MyBookingRepository extends JpaRepository<Book,Long>{

	 Optional<Book> findByBookingIdAndPassengerName(String bookingId, String passengerName);
}
