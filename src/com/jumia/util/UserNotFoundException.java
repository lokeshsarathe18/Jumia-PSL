package com.jumia.util;

import com.jumia.bean.ExceptionMessage;


public class UserNotFoundException extends JumiaException {

	private static final long serialVersionUID = -8726563052769816274L;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}
}
