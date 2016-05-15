package sample.cloudmeetingroom.booking.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * @author lionel stephane
 */
public class ValidationError implements Serializable {
    
    private String target;
    private String message;
    private String code;
    private ValidationError.Type type;
    
    public ValidationError() { }

    public ValidationError(String target, String code, String message, Type type) {
        this.target = target;
        this.message = message;
        this.code = code;
        this.type = type;
    }
    
    public ValidationError(String target, String message, Type type) {
        this(target, null, message, type);
    }

    @Override
    public String toString() {
        return "ValidationError{" + "target=" + target + ", message=" + message + ", code=" + code + ", type=" + type + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.target);
        hash = 97 * hash + Objects.hashCode(this.code);
        hash = 97 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ValidationError other = (ValidationError) obj;
        if (!Objects.equals(this.target, other.target)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }
    
    public static ValidationError newObjectValidationError(String target, String message) {
        return newObjectValidationError(target, null, message);
    }
    
    public static ValidationError newObjectValidationError(String target, String code, String message) {
        return new ValidationError(target, code, message, Type.OBJECT);
    }
    
    public static ValidationError newFieldValidationError(String target, String message) {
        return newFieldValidationError(target, null, message);
    }
    
    public static ValidationError newFieldValidationError(String target, String code, String message) {
        return new ValidationError(target, code, message, Type.FIELD);
    }
    
    public static Collection<ValidationError> getAll(Errors errors){
        Collection<ValidationError> validationErrors = new ArrayList(errors.getErrorCount());
        for(ObjectError or: errors.getAllErrors()){
            if(or instanceof FieldError){
                FieldError fe = (FieldError)or;
                validationErrors.add(newFieldValidationError(fe.getField(), fe.getCode(), fe.getDefaultMessage()));
            }
            else{    
                validationErrors.add(newObjectValidationError(or.getObjectName(), or.getCode(), or.getDefaultMessage()));   
            }
        }
        return validationErrors;
    } 

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    public enum Type {
        FIELD, OBJECT;        
    }
}
