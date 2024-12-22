package exceptions;

public class InterpolationException extends RuntimeException{
    public InterpolationException(){

    }
    public InterpolationException(String message){
        throw new RuntimeException(message);
    }
}
