package sample.cloudmeetingroom.ui.web;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sample.cloudmeetingroom.ui.model.RoomBooking;

/**
 * @author lionel ngounou
 */
@Controller
@RequestMapping("/bookings")
public class BookingController {
	
	@Autowired
	BookingServiceClient bookingServiceClient; 
	
    @ModelAttribute("generalModel")
    public Map<String,Object> generalModel(){
        Map<String,Object> gm = new HashMap(1);
        gm.put("section", "bookings");
        return gm;
    }	
	
    @RequestMapping
    public String list(){
        return "booking/list";
    }
	
	@RequestMapping(value = "/api", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map> listAPI(){
        Map body = new HashMap(2);
        body.put("success", true);
        body.put("roomBookings", bookingServiceClient.getRoomBookings());
        return new ResponseEntity(body, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/api", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public ResponseEntity<Map> createAPI(@RequestBody RoomBooking roomBooking){
		System.out.println("\t ||| create room booking " + roomBooking);
		bookingServiceClient.postRoomBooking(roomBooking);
        Map body = new HashMap(2);
        body.put("success", true);
        body.put("roomBooking", roomBooking);
        return new ResponseEntity(body, HttpStatus.CREATED);
    }
}
