package com.usermgm.util;

import lombok.Getter;

@Getter
public class ErrorStructure<T> {

	private int statuscode;
	private String errorMessage;
	private T rootCause;

	public ErrorStructure<T> setStatuscode(int statuscode) {
		this.statuscode = statuscode;
		return this;
	}

	public ErrorStructure<T> setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}

	public ErrorStructure<T> setRootCause(T rootCause) {
		this.rootCause = rootCause;
		return this;
	}

}
