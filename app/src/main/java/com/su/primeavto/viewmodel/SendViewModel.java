package com.su.primeavto.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.su.primeavto.ErrorMessenger;
import com.su.primeavto.net.ApiError;
import com.su.primeavto.repository.SendMessageRepository;


import okhttp3.ResponseBody;

public class SendViewModel extends ViewModel implements ErrorMessenger {

    private static final SendMessageRepository REPOSITORY = SendMessageRepository.getInstance();

    public LiveData<Boolean> sendMessage(String login, String pass, String phone, String text) {
        return REPOSITORY.sendMessage(login, pass, phone, text);
    }

    @Override
    public LiveData<ApiError> getErrorObserver() {
        return REPOSITORY.getErrorObserver();
    }

    public LiveData<Integer> getUserStatus(String phone, String token) {
        return REPOSITORY.getUserStatus(phone, token);
    }

    public LiveData<String> addUser(String phone, String token) {
        return REPOSITORY.addUser(phone, token);
    }

    public void sendDate(String token, int fullPrice, String title, String body) {
//        return REPOSITORY.sendDate(token, fullPrice, title,  body);
    }
}
