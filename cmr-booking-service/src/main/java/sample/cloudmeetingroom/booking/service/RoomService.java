package sample.cloudmeetingroom.booking.service;

import java.util.Collection;
import java.util.stream.Collectors;
import sample.cloudmeetingroom.booking.model.Room;
import sample.cloudmeetingroom.booking.model.TimeSlot;

/**
 * @author lionel ngounou
 */
public interface RoomService {

	public default Collection<String> listRooms(){
		return Room.stream().map(Room::toString).collect(Collectors.toList());
	}

	public default Collection<String> listTimeSlots(){
		return TimeSlot.stream().map(TimeSlot::toString).collect(Collectors.toList());
	}
	
}
