package exceptions;

public class InconsistentFunctionsException extends RuntimeException {
    public InconsistentFunctionsException() {
    }
    public InconsistentFunctionsException(String message){
        throw new RuntimeException(message);
    }
}
