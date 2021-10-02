package com.census;

public class CustomException extends Exception {
	
	public enum ExceptionType {
        FILE_NOT_FOUND,INCORRECT_TYPE,DELIMITER_NOT_FOUND,INCORRECT_HEADER
    }
	
	public ExceptionType type;
	
	public CustomException(ExceptionType type, String msg) {
		super(msg);
		this.type = type;
	}
	
	public CustomException(ExceptionType type) {
        this.type = type;
    }
}
