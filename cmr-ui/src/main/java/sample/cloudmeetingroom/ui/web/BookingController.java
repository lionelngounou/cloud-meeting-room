package sample.cloudmeetingroom.ui.web;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	
	@RequestMapping(value = "/api", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map> createAPI(@RequestBody RoomBookingForm roomBookingForm){
		bookingServiceClient.postRoomBooking(roomBookingForm);
        Map body = new HashMap(2);
        body.put("success", true);
        body.put("roomBooking", roomBookingForm);
        return new ResponseEntity(body, HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/api/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Map deleteAPI(@PathVariable(value="id") Integer id){
		bookingServiceClient.deleteRoomBooking(id);
        Map body = new HashMap(1);
        body.put("success", true);
        return body;
    }
}
