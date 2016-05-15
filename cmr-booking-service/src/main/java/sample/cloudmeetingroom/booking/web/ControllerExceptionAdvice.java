package sample.cloudmeetingroom.booking.web;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.validation.ValidationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author lionel stephane
 * */
@ControllerAdvice("sample.cloudmeetingroom.booking.web")
public class ControllerExceptionAdvice {
    
    static Log log = LogFactory.getLog(ControllerExceptionAdvice.class.getName());
        
    public static final Map<String, Object> SUCCESS_MAP = new LinkedHashMap(1);
    public static final Map<String, Object> FAIL_MAP = new LinkedHashMap(1);    
    static {
        SUCCESS_MAP.put("success", true);
        FAIL_MAP.put("success", false);
    }
	    
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ValidationException.class, DataAccessException.class, SQLException.class})
    @ResponseBody
    public Map<String, Object> handleDataConflict(Exception ex){ 
        log.error("handle data conflict : " + ex.getMessage());
        return handleCommon(ex);
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseBody
    public Map<String, Object> handleNotFound(ResourceNotFoundException ex){ 
        log.error("handle data not found : " + ex.getMessage());
        return handleCommon(ex);
    }
    
    private Map<String, Object> handleCommon(Exception ex){ 
        Map<String, Object> em = newFailureMap();
        em.put("message", ex.getMessage());
        return em;
    }
    
    private static Map<String, Object> newFailureMap(){
        return new LinkedHashMap(FAIL_MAP);
    }
}
                