package com.trainticket.TrainBooking.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trainticket.TrainBooking.Pojo.Book;
import com.trainticket.TrainBooking.Respository.MyBookingRepository;
import com.trainticket.TrainBooking.Service.CancellingService;

@Controller
public class CancellingController {

	@Autowired
	MyBookingRepository bookingRepository;
	
	@Autowired
	CancellingService cancellingService;
	
	@GetMapping("/cancelTrain")
    public String cancelTrains() {
        return "cancelTrain";  
    }
	
	@PostMapping("/cancelBooking")
    public String cancelBooking(@RequestParam("bookingId") String bookingId,
                                @RequestParam("passengerName") String passengerName,
                                Model model) {
        // Fetch the booking details using the service
        Optional<Book> booking = cancellingService.findByBookingIdAndPassengerName(bookingId, passengerName);

        if (booking.isPresent()) {
            // If booking is found, add it to the model and return the table view
            model.addAttribute("booking", booking.get());
            return "bookingDetails"; // Name of the JSP page showing booking details
        } else {
            // If booking is not found, return to the same page with an error message
            model.addAttribute("error", "Booking not found for the given ID and name.");
            return "cancelBooking"; // Return to the form page
        }
    }
	
	
}
