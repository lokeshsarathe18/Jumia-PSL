package com.jumia.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="exception-message")
public class ExceptionMessage extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;

	public ExceptionMessage() {
		super();
	}

	public ExceptionMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
