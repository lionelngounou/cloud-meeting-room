package sample.cloudmeetingroom.booking.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.stream.Stream;

/**
 * @author lionel.ngounou
 */
public enum Room {
    
    A125, A135, A145, B203;

    @Override
    public String toString() {
        return "Room["+ name() +"]"; 
    }
    
    public static Stream<Room> stream(){
        return Stream.of(values());
    }
	
	@JsonCreator
	public static Room findByName(final String name){
		return stream().filter(r -> r.name().equals(name)).findAny().orElse(null);
	}
}
