package com.trainticket.TrainBooking.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.trainticket.TrainBooking.Pojo.Train;
import com.trainticket.TrainBooking.Service.MytrainService;



@Controller
public class MytrainController {
	
	@Autowired
    private MytrainService mytrainService;

	@GetMapping("/")
	public String getLoginPage() {
		
		return "loginpage";
	}
	
	@GetMapping("/signup")
	public String getSignUpPage() {
		
		return "signup";
	}
	
	 @GetMapping("/searchTrains")
	    public String searchTrains() {
	        return "searchTrain";  // Return the JSP page to display results
	    }
	   @GetMapping("/result")
	    public String searchTrains(@RequestParam("source") String source,
	                               @RequestParam("destination") String destination,
	                               Model model) {
	        // Call the service to fetch available trains between source and destination
	        List<Train> trains = mytrainService.findTrainsBetweenStations(source, destination);

	        // Add the list of trains to the model to display on the results page
	        model.addAttribute("trains", trains);
	        return "searchTrainResult";  // Return the JSP page to display the results
	    }
	   
}
