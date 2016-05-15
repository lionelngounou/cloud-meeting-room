package sample.cloudmeetingroom.booking.repository.memory;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;
import sample.cloudmeetingroom.booking.model.Room;
import sample.cloudmeetingroom.booking.model.RoomBooking;
import sample.cloudmeetingroom.booking.model.TimeSlot;
import sample.cloudmeetingroom.booking.repository.RoomBookingRepository;

/**
 * @author lionel ngounou
 */
@Repository
public class InMemoryRoomBookingRepository implements RoomBookingRepository {
	
	private final Set<RoomBooking> bookings = new CopyOnWriteArraySet();	
	private final AtomicInteger ID_SEQ = new AtomicInteger(100);
			
	@Override
	public Collection<RoomBooking> findAll() {
		return Collections.unmodifiableCollection(bookings);
	}

	@Override
	public <S extends RoomBooking> S save(S roomBooking) {
		if(!bookings.add(roomBooking))
			throw new UnsupportedOperationException("Booking already exists"); 
		if(roomBooking.getId() == null)
			roomBooking.setId(ID_SEQ.getAndIncrement());
		return roomBooking;
	}

	@Override
	public Optional<RoomBooking> findById(Integer id) {
		return bookings.stream().filter(rb -> Objects.equals(rb.getId(), id)).findAny();
	}

	@Override
	public boolean exists(Integer id) {
		return bookings.stream().anyMatch(rb -> Objects.equals(rb.getId(), id));
	}

	@Override
	public long count() {
		return bookings.size();
	}

	@Override
	public boolean bookingExists(Room room, TimeSlot timeSlot, LocalDate date) {
		return bookings.stream().anyMatch(rb -> Objects.equals(rb.getRoom(), room)
				&& Objects.equals(rb.getTimeSlot(), timeSlot)
				&& Objects.equals(rb.getDate(), date)
			);
	}

	@Override
	public void delete(RoomBooking roomBooking) {
		bookings.remove(roomBooking);
	}
	
	
}
