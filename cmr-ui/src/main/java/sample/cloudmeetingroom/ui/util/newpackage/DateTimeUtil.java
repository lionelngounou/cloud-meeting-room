package sample.cloudmeetingroom.ui.util.newpackage;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author lionel ngounou
 */
public final class DateTimeUtil {

	private DateTimeUtil() {}
	
	public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
	public static final SimpleDateFormat DATE_FMT = new SimpleDateFormat(DATE_PATTERN);
	
	public static class JsonDateDeserializer extends JsonDeserializer<Date>{	
		@Override
		public Date deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
			try {
				String date = jp.getText();
				return DATE_FMT.parse(date);
			} catch (ParseException ex) {
				throw new RuntimeException(ex);
			}
		}		
	}
	
}
