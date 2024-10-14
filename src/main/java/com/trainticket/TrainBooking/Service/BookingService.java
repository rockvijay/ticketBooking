package com.trainticket.TrainBooking.Service;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.itextpdf.text.pdf.PdfWriter;
import com.trainticket.TrainBooking.Pojo.Book;
import com.trainticket.TrainBooking.Respository.MyBookingRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class BookingService {

	@Autowired
	MyBookingRepository myBookingRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	 EmailService emailService;
	
//	 @Autowired
//	    PdfService pdfService;
	
	static String externalURL="http://localhost:8081";
	
	 public List<Book> addPassengerToBooking(
	            Long trainId, String name, double price,
	            String passengerName, int age, int gender, HttpSession session) {
	            
	            if(trainId == null) {
	                trainId = (Long) session.getAttribute("trainId");
	                name = (String) session.getAttribute("name");
	                price = (double) session.getAttribute("price");
	            } else {
	                session.setAttribute("trainId", trainId);
	                session.setAttribute("name", name);
	                session.setAttribute("price", price);
	            }
	            
	            List<Book> passengers = (List<Book>) session.getAttribute("passengers");
	            if (passengers == null) {
	                passengers = new ArrayList<>();
	            }
	            
	            int nextSeatIndex = passengers.size() + 1;
	            
	            Book newPassenger = new Book();
	            newPassenger.setTrainId(trainId);
	            newPassenger.setName(name);
	            newPassenger.setPassengerName(passengerName);
	            newPassenger.setAge(age);
	            newPassenger.setGender(gender);
	            newPassenger.setBookingDate(LocalDate.now());
	            newPassenger.setPrice(price);
                newPassenger.setSeatIndex(nextSeatIndex);
	           
	           
	            passengers.add(newPassenger);
	            
	            
	            session.setAttribute("passengers", passengers);
	            return passengers;
	        }
	 
//---------------------------------------------------------------------------------------------------
	 
     public double calculateTotalPrice(List<Book> passengers) 
     {
         double totalPrice = 0.0;
         
         for (Book passenger : passengers) {
             totalPrice += passenger.getPrice();
         }
         return totalPrice;
     }

//--------------------------------------------------------------------------------------------------
     
     public void saveAllPassengers(List<Book> passengers) {
    	 
    	 String url=externalURL+"/irctc/bookedPassengers";
    	// myBookingRepository.saveAll(passengers);
    	 restTemplate.postForEntity(url, passengers, String.class);
     }
     
     public void saveToMyRepository(List<Book> passengers) {
    	 myBookingRepository.saveAll(passengers);
     }
     
//-------------------------------------------------------------------------------------------------     
    
     public byte[] generateConfirmationPdf(Long trainId, List<Book> passengers, String source, String destination) {
    	    Document document = new Document();
    	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    	    try {
    	        PdfWriter.getInstance(document, byteArrayOutputStream);
    	        document.open();

    	        document.add(new Paragraph("Booking Confirmation"));
    	        document.add(new Paragraph("Source: " + source));
    	        document.add(new Paragraph("Destination: " + destination));
    	        document.add(new Paragraph("Passenger Details:"));

    	        for (Book passenger : passengers) {
    	            String pnr = passenger.getPnr(); // Retrieve PNR from passenger
    	            document.add(new Paragraph("PNR: " + pnr + " - Name: " + passenger.getPassengerName()));
    	            document.add(new Paragraph("Age: " + passenger.getAge()));
    	            document.add(new Paragraph("Gender: " + (passenger.getGender() == 1 ? "Male" : "Female")));
    	            document.add(new Paragraph("Seat Index: " + passenger.getSeatIndex()));
    	            document.add(new Paragraph("Price: " + passenger.getPrice()));
    	            document.add(new Paragraph("-----------------------------"));
    	        }

    	        document.close();
    	    } catch (DocumentException e) {
    	        e.printStackTrace();
    	    }

    	    return byteArrayOutputStream.toByteArray(); // Return PDF as byte array
    	}

//----------------------------------------------------------------------------------------------------------------
     
     public String fetchPnrFromExternalService(String bookingId) {
    	    String url = externalURL + "/irctc/getPnr/" + bookingId;

    	    try {
    	    	
    	        String pnr = restTemplate.getForObject(url, String.class);
    	        return pnr;  // Return the PNR fetched from the external service
    	        
    	    } catch (HttpClientErrorException e) {
    	        // Handle 404 error or other HTTP errors
    	        if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
    	            return "404 PNR not found for booking ID: " + bookingId;
    	        } else {
    	            // Handle other errors (e.g., 500 Internal Server Error)
    	            return "500 PNR fetch failed due to error: " + e.getMessage();
    	        }
    	    } catch (Exception e) {
    	        // Catch any other exceptions and return a failure message
    	        return "PNR fetch failed: " + e.getMessage();
    	    }
    	}

//-----------------------------------------------------------------------------------------------------------------
     
     public String generateBookingId() {
         // Get the current timestamp in a specific format
         String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

         // Generate a random number
         Random random = new Random();
         int randomNumber = 1000 + random.nextInt(9000);  // 4-digit random number
         String bookingidd = "Book"+timestamp;
         // Combine the timestamp and random number to form the booking ID
         return bookingidd;
     }
}
