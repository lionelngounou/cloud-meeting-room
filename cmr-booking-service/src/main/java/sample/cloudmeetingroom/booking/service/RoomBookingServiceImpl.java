package sample.cloudmeetingroom.booking.service;

import java.util.Collection;
import java.util.Optional;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.cloudmeetingroom.booking.model.Room;
import sample.cloudmeetingroom.booking.model.RoomBooking;
import sample.cloudmeetingroom.booking.model.TimeSlot;
import sample.cloudmeetingroom.booking.repository.RoomBookingRepository;

/**
 * @author lionel ngounou
 */
@Service
public class RoomBookingServiceImpl implements RoomBookingService{

	private final RoomBookingRepository roomBookingRepository;
	
	@Autowired
	public RoomBookingServiceImpl(RoomBookingRepository roomBookingRepository) {
		this.roomBookingRepository = roomBookingRepository;
	}	
	
	@Override
	public Collection<RoomBooking> listAll() {
		return roomBookingRepository.findAll(); 
	}
	
	@Override
	public boolean exists(Room room, TimeSlot timeSlot, LocalDate date) {
		return roomBookingRepository.bookingExists(room, timeSlot, date);
	}

	@Override
	public boolean exists(Integer id) {
		return roomBookingRepository.exists(id);
	}	
	
	@Override
	public Optional<RoomBooking> findById(Integer id) {
		return roomBookingRepository.findById(id);
	}

	@Override
	public void delete(RoomBooking roomBooking) {
		roomBookingRepository.delete(roomBooking);
	}

	@Override
	public void create(RoomBooking roomBooking) {
		roomBooking.setDateCreated(LocalDate.now());
		roomBookingRepository.save(roomBooking);
	}
}
