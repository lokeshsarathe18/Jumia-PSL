package com.jumia.util;

import javax.xml.bind.annotation.XmlElementRef;

import com.jumia.bean.ExceptionMessage;

public class JumiaException extends Exception {

	private static final long serialVersionUID = 1666497646172567401L;
	
	private ExceptionMessage exceptionMessage;

	public JumiaException() {
		super();
	}

	public JumiaException(ExceptionMessage exceptionMessage) {
		super();
		this.exceptionMessage = exceptionMessage;
	}

	@XmlElementRef
	public ExceptionMessage getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(ExceptionMessage exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		//return super.fillInStackTrace();
		return this;
	}
}
