package sample.cloudmeetingroom.ui.web;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import sample.cloudmeetingroom.ui.model.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author lionel ngounou
 */
@Component
public class BookingServiceClientImpl implements BookingServiceClient {	
	
	private static final String BASE_URL = "http://localhost:8089/api/v1/rooms";
	//private static final String CONTENT_TYPE = "application/vnd.org.jfrog.artifactory.security.Group+json";

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
	public void postRoomBooking(RoomBooking roomBooking){
		ResponseEntity<Map> response = restTemplate.postForEntity(BASE_URL + "/bookings", roomBooking, Map.class);
		//return null;
	}

	@Override
	public void deleteRoomBooking(int id) {
		restTemplate.delete(BASE_URL + "/bookings/" + id);
	}
	
}
