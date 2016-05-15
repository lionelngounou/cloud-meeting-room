package sample.cloudmeetingroom.booking.web;


//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sample.cloudmeetingroom.booking.model.Room;
import sample.cloudmeetingroom.booking.model.TimeSlot;

/**
 * @author lionel ngounou
 */
@RequestMapping(value = "/api/v1/rooms")
@RestController
public class RoomController {
	
    @RequestMapping(method = RequestMethod.GET)
	public Room[] listRooms(){
		return Room.values(); 
	}
	
    @RequestMapping(value="/timeSlots", method = RequestMethod.GET)
	public TimeSlot[] listTimeSlots(){
		return TimeSlot.values(); 
	}
	
}
