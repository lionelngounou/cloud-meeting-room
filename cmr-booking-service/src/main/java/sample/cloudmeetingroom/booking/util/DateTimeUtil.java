package sample.cloudmeetingroom.booking.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


/**
 * @author lionel ngounou
 */
public final class DateTimeUtil {

	private DateTimeUtil() {}
	
	public static final String DATE_PATTERN = "dd/MM/yyyy";
	public static final String TIME_PATTERN = "HH:mm";
	public static final DateTimeFormatter DATE_FMT = DateTimeFormat.forPattern(DATE_PATTERN);
	public static final DateTimeFormatter TIME_FMT = DateTimeFormat.forPattern(TIME_PATTERN);
	
	public final static String format(LocalTime time){
		return time.toString(TIME_FMT);
	}
	
	public final static String format(LocalDate date){
		return date.toString(DATE_FMT);
	}
	
	public static class JsonDateSerializer extends JsonSerializer<LocalDate>{
		@Override
		public void serialize(LocalDate t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
			jg.writeString(format(t));
		}		
	}
	
	public static class JsonDateDeserializer extends JsonDeserializer<LocalDate>{	
		@Override
		public LocalDate deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
			String date = jp.getText();
			return DATE_FMT.parseLocalDate(date);
		}		
	}
	
}
