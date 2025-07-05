package io.github.mannam11.exception;

public class ReplicateException extends RuntimeException{

    public ReplicateException(String message){
        super(message);
    }

    public ReplicateException(String message, Throwable cause){
        super(message,cause);
    }
}
