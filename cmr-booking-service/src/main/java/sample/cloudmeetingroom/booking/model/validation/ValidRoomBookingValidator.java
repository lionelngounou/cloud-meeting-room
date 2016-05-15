package sample.cloudmeetingroom.booking.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import sample.cloudmeetingroom.booking.model.RoomBooking;
import sample.cloudmeetingroom.booking.service.RoomBookingService;

/**
 * @author lionel ngounou
 */
class ValidRoomBookingValidator implements ConstraintValidator<ValidRoomBooking, RoomBooking> {

	@Autowired
	private RoomBookingService roomBookingService; 
			
	@Override
	public void initialize(ValidRoomBooking a) { }

	@Override
	public boolean isValid(RoomBooking roomBooking, ConstraintValidatorContext cvc) {
		if(roomBooking==null) 
			return true;
		return !roomBookingService.exists(roomBooking.getId())
			&& !roomBookingService.exists(roomBooking.getRoom(), roomBooking.getTimeSlot(), roomBooking.getDate());
	}	
}
