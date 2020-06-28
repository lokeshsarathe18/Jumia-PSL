package com.jumia.util;

import com.jumia.bean.ExceptionMessage;


public class ProductNotFoundException extends JumiaException {
	
	private static final long serialVersionUID = -4490882794182328862L;

	public ProductNotFoundException() {
		super();
	}

	public ProductNotFoundException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}
}
