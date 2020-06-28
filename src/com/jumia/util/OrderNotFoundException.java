package com.jumia.util;

import com.jumia.bean.ExceptionMessage;

public class OrderNotFoundException extends JumiaException {
	private static final long serialVersionUID = 4174143153994349652L;

	public OrderNotFoundException() {
		super();
	}

	public OrderNotFoundException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}
}
