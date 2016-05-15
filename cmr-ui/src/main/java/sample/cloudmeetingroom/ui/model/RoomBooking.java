package sample.cloudmeetingroom.ui.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Lionel Ngounou
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomBooking implements Serializable {
    
    private Integer id;
	
    private String user, room, date;
	
	private TimeSlot timeSlot;

	private Boolean past, inProgress;

    public RoomBooking() {    }

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

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public Boolean isPast(){
		return past;
	}
    
    public void setInPast(Boolean past) {
        this.past = past;
    }

	public Boolean isInProgress(){
		return inProgress;
	}

    public void setInProgress(Boolean inProgress) {
        this.inProgress = inProgress;
    }
	
    private static final long serialVersionUID = -2821L;
    
}
