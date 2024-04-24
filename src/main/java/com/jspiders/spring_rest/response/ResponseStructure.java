package com.jspiders.spring_rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStructure<T> {
	
	private T data;
	private int status;
	private String message;
	
}
