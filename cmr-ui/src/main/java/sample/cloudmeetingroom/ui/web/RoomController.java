package sample.cloudmeetingroom.ui.web;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lionel ngounou
 */
@Controller
@RequestMapping("/rooms")
public class RoomController {
	
	@Autowired
	BookingServiceClient bookingServiceClient; 
	
    @ModelAttribute("generalModel")
    public Map<String,Object> generalModel(){
        Map<String,Object> gm = new HashMap(1);
        gm.put("section", "bookings");
        return gm;
    }	
	
	@RequestMapping(value = "/api", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map> listRoomsAPI(){
		System.out.println("@@@@@@@@@@@@@@@ list rooms API............s");
        Map body = new HashMap(2);
        body.put("success", true);
        body.put("rooms", bookingServiceClient.getRooms());
        return new ResponseEntity(body, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/api/timeSlots", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map> listTimeSlotsAPI(){
        Map body = new HashMap(2);
        body.put("success", true);
        body.put("timeSlots", bookingServiceClient.getTimeSlots());
        return new ResponseEntity(body, HttpStatus.OK);
    }
}
