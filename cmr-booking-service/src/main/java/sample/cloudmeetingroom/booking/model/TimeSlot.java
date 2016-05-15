package sample.cloudmeetingroom.booking.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.Interval;
import static sample.cloudmeetingroom.booking.util.DateTimeUtil.*;

/**
 * @author lionel ngounou
 */
@JsonPropertyOrder(value = {"id", "description"})
public enum TimeSlot implements Comparable<TimeSlot>{
	
	_8_TO_9   (time(8) , time(9)),
	_9_TO_11  (time(9) , time(11)),
	_11_TO_12 (time(11), time(12)),
	_12_TO_13 (time(12), time(13)),
	_13_TO_15 (time(13), time(15)),
	_15_TO_17 (time(15), time(17)),
	_17_TO_19 (time(17), time(19));
	
	private final Interval i;

	private TimeSlot(DateTime from, DateTime to) {
		i = new Interval(from, to);
	}

	@Override
	public String toString() {
		return format(getLocalTimeFrom()) + " - " + format(getLocalTimeTo());
	}
		
	@JsonValue
	public Map<String, Object> asMap(){
		Map<String, Object> map = new HashMap(2);
		map.put("id", name());
		map.put("description", toString());
		return map;
	}
	
	@JsonCreator
	public static TimeSlot findByName(final String name){
		return stream().filter(ts -> ts.name().equals(name)).findAny().orElse(null);
	}

	public LocalTime getLocalTimeFrom() {
		return i.getStart().toLocalTime();
	}
	
	public String getId(){
		return name();
	}

	public LocalTime getLocalTimeTo() {
		return i.getEnd().toLocalTime();
	}	
	
	public Interval getInterval(){
		return i;
	}
	
	public boolean intervalContains(LocalTime time){
		return i.contains(time(time.getHourOfDay(), time.getMinuteOfHour()) );
	}
	
	public boolean isNow(){
		return intervalContains(new LocalTime());
	}
	
	private static DateTime time(int hourOfDay){
		return time(hourOfDay, 00);
	}
	
	private static DateTime time(int hourOfDay, int minuteOfHour){
		return new DateTime(1970, 1, 1, hourOfDay, minuteOfHour);
	}
	
    public static Stream<TimeSlot> stream(){
        return Stream.of(values());
    }
	
}
