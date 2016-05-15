package sample.cloudmeetingroom.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;
import sample.cloudmeetingroom.booking.model.validation.ValidRoomBooking;
import static sample.cloudmeetingroom.booking.util.DateTimeUtil.*;

/**
 * @author Lionel Ngounou
 */
@ValidRoomBooking
@JsonIgnoreProperties({"dateCreated"})
public class RoomBooking implements Serializable {
    
    private Integer id;   
	
	@NotNull
	@Size(max = 50)
    private String user;
	
	@NotNull
	private Room room;
	
	@NotNull
	private TimeSlot timeSlot;
	
	@NotNull
	@DateTimeFormat(pattern = DATE_PATTERN)
	private LocalDate date; 
	
	private LocalDate dateCreated; 	

    public RoomBooking() {    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 23 * hash + Objects.hashCode(this.id);
		hash = 23 * hash + Objects.hashCode(this.room);
		hash = 23 * hash + Objects.hashCode(this.timeSlot);
		hash = 23 * hash + Objects.hashCode(this.date);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final RoomBooking other = (RoomBooking) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		if (this.room != other.room) {
			return false;
		}
		if (this.timeSlot != other.timeSlot) {
			return false;
		}
		return Objects.equals(this.date, other.date);
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
	@JsonSerialize(using = JsonDateSerializer.class)
	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	/**Meeting already started or finished*/
	public boolean isPast(){
		LocalDate nowDate = LocalDate.now();
		if(nowDate.isAfter(date)) 
			return true;
		return nowDate.isEqual(date) && new LocalTime().isAfter(timeSlot.getLocalTimeFrom());
	}
	
	/**Meeting in current date and time*/
	public boolean isInProgress(){
		return new LocalDate().isEqual(date) && timeSlot.isNow();
	}
	
	/*
	public Map<String, Object> asMap(){
		Map<String, Object> map = new HashMap(7);
		map.put("id", id);
		map.put("date", format(date));
		map.put("user", user);
		map.put("timeSlot", timeSlot);
		map.put("past", isPast());
		map.put("inProgress", isInProgress());
		map.put("room", room);
		return map;
	}*/

    private static final long serialVersionUID = -2821L;
    
}
