package exceptions;

public class ArrayIsNotSortedException extends RuntimeException{
    public ArrayIsNotSortedException(){

    }
    public  ArrayIsNotSortedException(String message){
        throw new RuntimeException(message);
    }
}
