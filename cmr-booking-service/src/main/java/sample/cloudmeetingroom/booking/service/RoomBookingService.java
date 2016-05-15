
package sample.cloudmeetingroom.booking.service;

import java.util.Collection;
import java.util.Optional;
import org.joda.time.LocalDate;
import sample.cloudmeetingroom.booking.model.Room;
import sample.cloudmeetingroom.booking.model.RoomBooking;
import sample.cloudmeetingroom.booking.model.TimeSlot;

/**
 * @author lionel ngounou
 */
public interface RoomBookingService {

	public Collection<RoomBooking> listAll();
	
	public boolean exists(Room room, TimeSlot timeSlot, LocalDate date);
	
	public boolean exists(Integer id);
	
	public Optional<RoomBooking> findById(Integer id);

	public void create(RoomBooking roomBooking);
		
	public void delete(RoomBooking roomBooking);
	
}
