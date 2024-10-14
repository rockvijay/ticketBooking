package com.trainticket.TrainBooking.Pojo;

import java.time.LocalTime;
import org.springframework.stereotype.Component;

@Component
public class Journey {

	private Long TrainID;
	private String TrainName;
	private String Origin;
	private String Destination;
	private LocalTime ArrivalTime;
	private LocalTime DepartureTime;
	public Long getTrainID() {
		return TrainID;
	}
	public void setTrainID(Long trainID) {
		TrainID = trainID;
	}
	public String getTrainName() {
		return TrainName;
	}
	public void setTrainName(String trainName) {
		TrainName = trainName;
	}
	public String getOrigin() {
		return Origin;
	}
	public void setOrigin(String origin) {
		Origin = origin;
	}
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}
	public LocalTime getArrivalTime() {
		return ArrivalTime;
	}
	public void setArrivalTime(LocalTime arrivalTime) {
		ArrivalTime = arrivalTime;
	}
	public LocalTime getDepartureTime() {
		return DepartureTime;
	}
	public void setDepartureTime(LocalTime departureTime) {
		DepartureTime = departureTime;
	}
	public Journey(Long trainID, String trainName, String origin, String destination, LocalTime arrivalTime,
			LocalTime departureTime) {
		super();
		TrainID = trainID;
		TrainName = trainName;
		Origin = origin;
		Destination = destination;
		ArrivalTime = arrivalTime;
		DepartureTime = departureTime;
	}
	public Journey() {
		super();
	}
}
