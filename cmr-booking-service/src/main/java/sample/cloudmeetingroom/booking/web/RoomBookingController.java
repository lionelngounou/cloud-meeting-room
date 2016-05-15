package sample.cloudmeetingroom.booking.web;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sample.cloudmeetingroom.booking.model.RoomBooking;
import sample.cloudmeetingroom.booking.service.RoomBookingService;

/**
 * @author lionel ngounou
 */
@RequestMapping(value = "/api/v1/rooms/bookings")
@RestController
public class RoomBookingController {
    
    static Log log = LogFactory.getLog(RoomBookingController.class.getName());
	
	private final RoomBookingService roomBookingService; 

	@Autowired
	public RoomBookingController(RoomBookingService roomBookingService) {
		this.roomBookingService = roomBookingService;
	}
	
    @RequestMapping(method = RequestMethod.GET)
	public Collection<RoomBooking> listBookings(){
		log.info("list room bookings");
		return roomBookingService.listAll(); 
	}
	
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@Valid @RequestBody RoomBooking roomBooking, BindingResult result) {
        log.info("create room booking with details : " + roomBooking);
		
        //validation handling
        if(result.hasErrors())
            return new ResponseEntity(getFailureMap(result), HttpStatus.CONFLICT);
        
		roomBookingService.create(roomBooking);
        return new ResponseEntity(roomBooking, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public Map delete(@PathVariable(value="id") Integer id){
        log.info("delete room booking by id : " + id);
        
        Optional<RoomBooking> roomBookingOpt  = roomBookingService.findById(id);
		if(!roomBookingOpt.isPresent())
			throw new ResourceNotFoundException("Room booking " + id + " not found");
		
        roomBookingService.delete(roomBookingOpt.get());
        return ControllerExceptionAdvice.SUCCESS_MAP;
    }
    
    protected Map getFailureMap(BindingResult result){
        Map<String, Object> em = new LinkedHashMap(ControllerExceptionAdvice.FAIL_MAP);
        em.put("errors", ValidationError.getAll(result));
        return em;
    }  
	
	
}
