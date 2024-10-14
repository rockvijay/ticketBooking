package com.trainticket.TrainBooking.Controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.trainticket.TrainBooking.Pojo.Book;
import com.trainticket.TrainBooking.Service.BookingService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/create")
    public String createBooking(HttpSession session, Model model) {
        // Retrieve train details from the session
        Long trainId = (Long) session.getAttribute("trainId");
        String name = (String) session.getAttribute("name");
        Double price = (Double) session.getAttribute("price");
        
        model.addAttribute("trainId", trainId);
        model.addAttribute("name", name);
        model.addAttribute("price", price);
        
        // Pass the passengers list as well if needed
        List<Book> passengers = (List<Book>) session.getAttribute("passengers");
        model.addAttribute("passengers", passengers);
        System.out.println("Hello World!");
        return "bookingForm";
    }
    
    @PostMapping("/addPassenger")
    public String addPassenger(
        @RequestParam("trainId") Long trainId,
        @RequestParam("name") String name,
        @RequestParam("price") double price,
        @RequestParam("passengerName") String passengerName,
        @RequestParam("age") int age,
        @RequestParam("gender") int gender,
        HttpSession session,
        Model model
    ) {
        
        List<Book> passengers = bookingService.addPassengerToBooking(trainId, name, price, passengerName, age, gender, session);
        
        double totalPrice = bookingService.calculateTotalPrice(passengers);
        
        model.addAttribute("trainId", trainId);
        model.addAttribute("name", name);
        model.addAttribute("passengers", passengers);
        model.addAttribute("totalPrice", totalPrice);
        return "bookingForm";
    }
    
    //---------------------------------------------------------------------------------------------- 
    @GetMapping("/editPassenger")
    public String editPassenger(@RequestParam("seatIndex") int seatIndex, HttpSession session, Model model) {
        List<Book> passengers = (List<Book>) session.getAttribute("passengers");
        if (passengers != null ) {
           // Book passenger = passengers.get(seatIndex);
        	for(Book passenger : passengers)
        	{
        		if(passenger.getSeatIndex() == seatIndex)
        		{
        			model.addAttribute("passenger", passenger);
        			break;
        		}
        	}
        } 
        return "editPassengerForm";
    }
    
    @PostMapping("/updatePassenger")
    public String updatePassenger(Book updatedPassenger,HttpSession session) {

        List<Book> passengers = (List<Book>) session.getAttribute("passengers");
        if (passengers != null) {
            for (Book passenger : passengers) {
                if (passenger.getSeatIndex() == updatedPassenger.getSeatIndex()) {
                    passenger.setPassengerName(updatedPassenger.getPassengerName());
                    passenger.setAge(updatedPassenger.getAge());
                    passenger.setGender(updatedPassenger.getGender());
                    break;
                }
            }
            session.setAttribute("passengers", passengers);
        }
        return "redirect:/booking/create";  
    }
//--------------------------------------------------------------------------------------------------------------
    
    @PostMapping("/deletePassenger")
    public String deletePassenger(@RequestParam("seatIndex") int seatIndex, HttpSession session,Model model) {
        List<Book> passengers = (List<Book>) session.getAttribute("passengers");

        if (passengers != null) {
        	passengers.removeIf(passenger -> passenger.getSeatIndex() == seatIndex);
        	session.setAttribute("passengers", passengers);
        }
        model.addAttribute("passengers", passengers);
        return "redirect:/booking/create";  
    }

 //-------------------------------------------------------------------------------------------------------------
   
    
 // Review all passengers before finalizing the booking
//    @PostMapping("/confirm")
//    public String reviewBookingDetails(HttpSession session, Model model) {
//        // Retrieve passengers from session
//        List<Book> passengers = (List<Book>) session.getAttribute("passengers");
//        model.addAttribute("passengers", passengers);
//        if (passengers == null || passengers.isEmpty()) {
//            model.addAttribute("message", "No passengers have been added.");
//            return "reviewBooking"; 
//        }
//        double totalPrice = bookingService.calculateTotalPrice(passengers);
//        
//        // Generate booking IDs and fetch PNRs
//        List<String> pnrNumbers = new ArrayList<>();
//        
//        for (Book passenger : passengers) {
//            String bookingId = bookingService.generateBookingId();
//            passenger.setBookingId(bookingId);  // Set generated booking ID for each passenger
//            try {
//                String pnrNumber = bookingService.fetchPnrFromExternalService(passenger.getBookingId());
//                passenger.setPnr(pnrNumber);  // Assuming 'pnrNumber' is a field in the 'Book' entity
//                pnrNumbers.add(pnrNumber);
//            } catch (Exception e) {
//                // Log the error and set a default PNR or message for the passenger
//                passenger.setPnr("PNR fetch failed:");
//            }
//        }
//        bookingService.saveAllPassengers(passengers);
//        bookingService.saveToMyRepository(passengers); 
//        
//        session.removeAttribute("trainId");
//        session.removeAttribute("name");
//        session.removeAttribute("price");
//        
//        model.addAttribute("passengers", passengers);
//        model.addAttribute("totalPrice", totalPrice);
//        
//         return "afterConfirmationPage";  
//    }

    
    @PostMapping("/confirm")
    public String reviewBookingDetails(HttpSession session, Model model) {
        // Retrieve passengers from session
        List<Book> passengers = (List<Book>) session.getAttribute("passengers");
        model.addAttribute("passengers", passengers);

        if (passengers == null || passengers.isEmpty()) {
            model.addAttribute("message", "No passengers have been added.");
            return "reviewBooking";
        }

        double totalPrice = bookingService.calculateTotalPrice(passengers);

        // Generate booking IDs and fetch PNRs
        List<String> pnrNumbers = new ArrayList<>();
        for (Book passenger : passengers) {
            String bookingId = bookingService.generateBookingId();
            passenger.setBookingId(bookingId);  // Set generated booking ID for each passenger
            try {
                // Fetch PNR from external service
                String pnrNumber = bookingService.fetchPnrFromExternalService(passenger.getBookingId());
                passenger.setPnr(pnrNumber);  // Assuming 'pnrNumber' is a field in the 'Book' entity
                pnrNumbers.add(pnrNumber);
            } catch (Exception e) {
                // Log the error and set a default PNR or message for the passenger
                passenger.setPnr("PNR fetch failed");
            }
        }

        // Save passengers to both external and local repositories
        bookingService.saveAllPassengers(passengers);  // Save to external service
        bookingService.saveToMyRepository(passengers); // Save to local repository

        session.removeAttribute("trainId");
        session.removeAttribute("name");
        session.removeAttribute("price");

        model.addAttribute("passengers", passengers);
        model.addAttribute("totalPrice", totalPrice);

        return "afterConfirmationPage";
    }

 //--------------------------------------------------------------------------------------------------------- 
    
    
}
