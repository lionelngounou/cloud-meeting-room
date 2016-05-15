package sample.cloudmeetingroom.booking.repository;

import java.util.Collection;
import org.joda.time.LocalDate;
import sample.cloudmeetingroom.booking.model.Room;
import sample.cloudmeetingroom.booking.model.RoomBooking;
import sample.cloudmeetingroom.booking.model.TimeSlot;

/**
 * @author Lionel Ngounou
 */
public interface RoomBookingRepository extends CruRepository<RoomBooking, Integer> {
    
	public default void init(){
		System.out.println("\t ### init room booking repository");
		RoomBooking rb = new RoomBooking();
		rb.setDate(new LocalDate());
		rb.setRoom(Room.A145);
		rb.setUser("Lilly");
		rb.setTimeSlot(TimeSlot._15_TO_17);
		save(rb);
		rb = new RoomBooking();
		rb.setDate(new LocalDate().plusDays(2));
		rb.setRoom(Room.B203);
		rb.setUser("Pudinanu");
		rb.setTimeSlot(TimeSlot._12_TO_13);
		save(rb);
	}
	
	public Collection<RoomBooking> findAll();

	public boolean bookingExists(Room room, TimeSlot timeSlot, LocalDate date);

	public void delete(RoomBooking roomBooking);
    
}
