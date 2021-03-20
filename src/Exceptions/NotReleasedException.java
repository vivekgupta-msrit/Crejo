package Exceptions;

public class NotReleasedException extends RuntimeException{

    public NotReleasedException(String message){
        super(message);
    }
}
