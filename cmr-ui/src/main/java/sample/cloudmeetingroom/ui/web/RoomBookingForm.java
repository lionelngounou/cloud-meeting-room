package sample.cloudmeetingroom.ui.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import sample.cloudmeetingroom.ui.util.newpackage.DateTimeUtil.JsonDateDeserializer;

/**
 * @author lionel.ngounou
 */
public class RoomBookingForm implements Serializable {
	
    private String user, room, timeSlot;
	private Date date; 

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	
	@JsonDeserialize(using =JsonDateDeserializer.class )
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
