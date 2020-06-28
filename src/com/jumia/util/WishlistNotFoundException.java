package com.jumia.util;

import com.jumia.bean.ExceptionMessage;


public class WishlistNotFoundException extends JumiaException {

	private static final long serialVersionUID = 4589921570248464728L;

	public WishlistNotFoundException() {
		super();
	}

	public WishlistNotFoundException(ExceptionMessage exceptionMessage) {
		super(exceptionMessage);
	}
}
