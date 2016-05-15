package sample.friendso.user.web;

/**
 * @author lionel ngounou
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException() {
    }
    
}
