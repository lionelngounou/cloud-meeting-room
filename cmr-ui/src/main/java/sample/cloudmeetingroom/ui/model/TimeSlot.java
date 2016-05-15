package sample.cloudmeetingroom.ui.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Lionel Ngounou
 */
@JsonPropertyOrder(value = {"id", "description"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeSlot implements Serializable {
	
	private String id, description;

	public TimeSlot() { }

	@Override
	public String toString() {
        return ToStringBuilder.reflectionToString(this);
	}

	public String getId(){
		return this.id;
	}	

	public void setId(String id){
		this.id = id;
	}

	public String getDescription(){
		return this.description;
	}

	public void setDescription(String description){
		this.description = description;
	}
	
}
