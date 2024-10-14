package com.trainticket.TrainBooking.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.trainticket.TrainBooking.Pojo.Journey;
import com.trainticket.TrainBooking.Pojo.Train;

@Service
public class MytrainService {

	@Autowired
	private RestTemplate restTemplate;
	
	static String url="http://localhost:8081";
	
	
	public List<Journey> getTrainDetails()
	{

		String baseurl = url+"/";
		List<Journey> response = restTemplate.exchange(baseurl, HttpMethod.GET, null, new ParameterizedTypeReference <List<Journey>>() {
		}).getBody();
		return response;
	}
	
	 public String callLoginPageService()
	    {
		    String baseurl = url+"/"; // Replace with actual URL of the login page
	        ResponseEntity<String> response = restTemplate.getForEntity(baseurl, String.class);
	        return response.getBody();
	    }
	 
	 public List<Train> fetchAvailableTrains(String searchQuery) {
	        String externalServiceUrl = "http://localhost:8081/api/trains?search=" + searchQuery;

	        // Call the external service using RestTemplate and get a list of trains
	        Train[] trains = restTemplate.getForObject(externalServiceUrl, Train[].class);

	        return List.of(trains); // Convert array to list
	    }
	 public List<Train> findTrainsBetweenStations(String source, String destination) {
	        String url = "http://localhost:8081/irctc/list?source=" + source + "&destination=" + destination;
             
	        ResponseEntity<Train[]> response = restTemplate.getForEntity(url, Train[].class);
            
	        
	        if (response.getStatusCode() == HttpStatus.OK) {
	            Train[] trains = response.getBody();
	            return List.of(trains);
	        } else {
	            throw new RuntimeException("Failed to fetch trains, service returned status: " + response.getStatusCode());
	        }	    }
}
