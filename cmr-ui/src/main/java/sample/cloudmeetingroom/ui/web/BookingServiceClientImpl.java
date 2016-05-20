package sample.cloudmeetingroom.ui.web;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import sample.cloudmeetingroom.ui.model.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author lionel ngounou
 */
@Configuration
@PropertySource("classpath:app-keys.properties")
@Component
public class BookingServiceClientImpl implements BookingServiceClient {	
	
	@Value("${booking.service.url}")
	private String BASE_URL; // = "http://localhost:8089/api/v1/rooms";

	private final RestTemplate restTemplate = new RestTemplate(); 

	@Override
	public String[] getRooms(){
		String[] rooms = restTemplate.getForObject(BASE_URL, String[].class);
		return rooms;
	}
	
	@Override
	public TimeSlot[] getTimeSlots(){
		TimeSlot[] timeSlots = restTemplate.getForObject(BASE_URL + "/timeSlots", TimeSlot[].class);
		return timeSlots;
	}

	@Override
	public RoomBooking[] getRoomBookings(){
		RoomBooking[] roomBookings = restTemplate.getForObject(BASE_URL + "/bookings", RoomBooking[].class);
		return roomBookings;
	}

	@Override
	public void postRoomBooking(RoomBookingForm roomBookingForm){
		ResponseEntity<Map> response = restTemplate.postForEntity(BASE_URL + "/bookings", roomBookingForm, Map.class);
		//todo : deal with graceful response error - e.g validation;
	}

	@Override
	public void deleteRoomBooking(int id) {
		restTemplate.delete(BASE_URL + "/bookings/" + id);
	}
	
}
