package com.su.primeavto.net;

import androidx.annotation.NonNull;

public class ApiError {


	private final String errorMessage;


	public ApiError(final String errorMessage) {
		this.errorMessage = errorMessage;
	}


	@NonNull
	@Override
	public String toString() {
		return errorMessage;
	}
}