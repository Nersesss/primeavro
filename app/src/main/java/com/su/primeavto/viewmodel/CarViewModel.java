package com.su.primeavto.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.su.primeavto.ErrorMessenger;
import com.su.primeavto.model.AutoModel;
import com.su.primeavto.net.ApiError;
import com.su.primeavto.repository.CarRepository;

import java.io.File;
import java.util.List;

public class CarViewModel extends ViewModel implements ErrorMessenger {

    private static final CarRepository REPOSITORY = CarRepository.getInstance();

    public LiveData<List<AutoModel>> getCars(String token) {
        return REPOSITORY.getCars(token);
    }

    public LiveData<Boolean> sendDocs(String token, String phone, List<File> files) {
        return REPOSITORY.sendDocs(token, phone, files);
    }

    @Override
    public LiveData<ApiError> getErrorObserver() {
        return REPOSITORY.getErrorObserver();
    }
}
