package com.trainticket.TrainBooking.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trainticket.TrainBooking.Pojo.Book;
import com.trainticket.TrainBooking.Respository.MyBookingRepository;

@Service
public class CancellingService {

	@Autowired
	MyBookingRepository bookingRepository;

    public Optional<Book> findByBookingIdAndPassengerName(String bookingId, String passengerName) {
        return bookingRepository.findByBookingIdAndPassengerName(bookingId, passengerName);
    }
}
