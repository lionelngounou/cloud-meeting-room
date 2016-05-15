package sample.cloudmeetingroom.ui.web;

import sample.cloudmeetingroom.ui.model.*;

/**
 * @author lionel ngounou
 */
public interface BookingServiceClient {	
	
	public String[] getRooms();
	
	public TimeSlot[] getTimeSlots();

	public RoomBooking[] getRoomBookings();

	public void postRoomBooking(RoomBooking roomBooking);
	
	public void deleteRoomBooking(int id);
	
}
