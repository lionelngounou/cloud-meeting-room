package sample.cloudmeetingroom.booking.web;

/**
 * @author lionel ngounou
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException() {  }    
}
