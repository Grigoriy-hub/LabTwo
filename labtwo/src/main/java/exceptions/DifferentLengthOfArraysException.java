package exceptions;

public class DifferentLengthOfArraysException extends RuntimeException{
    public DifferentLengthOfArraysException(){

    }
    public DifferentLengthOfArraysException(String message){
        throw new RuntimeException(message);
    }
}
