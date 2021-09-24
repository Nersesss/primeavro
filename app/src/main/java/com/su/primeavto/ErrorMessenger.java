package com.su.primeavto;

import androidx.lifecycle.LiveData;

import com.su.primeavto.net.ApiError;

public interface ErrorMessenger {

	LiveData<ApiError> getErrorObserver();
}