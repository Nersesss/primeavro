package com.su.primeavto.repository;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.su.primeavto.App;
import com.su.primeavto.Constants;
import com.su.primeavto.ErrorMessenger;
import com.su.primeavto.model.UserStatusModel;
import com.su.primeavto.net.ApiError;
import com.su.primeavto.net.ApiService;
import com.su.primeavto.net.RetrofitHelper;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class SendMessageRepository implements ErrorMessenger {

    private final MutableLiveData<ApiError> apiError = new MutableLiveData<>();
    public static final ApiService apiService = RetrofitHelper.get();
    private static final SendMessageRepository INSTANCE = new SendMessageRepository();


    public static SendMessageRepository getInstance() {
        return INSTANCE;
    }


    public LiveData<Boolean> sendMessage(String login, String pass, String phone, String text) {
        final MutableLiveData<Boolean> data = new MutableLiveData<>();

        apiService.sendMessage("http://api.prostor-sms.ru/messages/v2/send", login, pass, phone, text).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   @NonNull Response<ResponseBody> response) {
                try {
                   if (response.body() != null && response.body().string().contains("error")){
                       Log.i(TAG, "onResponse: text = " + text);
                       Toast.makeText(App.getInstance(), response.body().string(), Toast.LENGTH_SHORT).show();
                       data.setValue(true);
                   }else {
                       data.setValue(response.isSuccessful());
                   }
                } catch (IOException e) {
                    e.printStackTrace();
                    data.setValue(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
                data.setValue(false);
            }
        });

        return data;
    }

    public LiveData<Integer> getUserStatus(String phone, String token) {
        final MutableLiveData<Integer> data = new MutableLiveData<>();
        apiService.getUserStatus(phone, token).enqueue(new Callback<UserStatusModel>() {
            @Override
            public void onResponse(@NonNull Call<UserStatusModel> call,
                                   @NonNull Response<UserStatusModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isSuccess() && response.body().isAccepted()) {
                        data.setValue(Constants.UserStatus.ACCEPTED.getValue());
                    }else if (response.body().isSuccess() && !response.body().isAccepted()){
                        data.setValue(Constants.UserStatus.SUCCESS.getValue());
                    }else {
                        data.setValue(Constants.UserStatus.DENIED.getValue());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserStatusModel> call, @NonNull Throwable t) {
                data.setValue(Constants.UserStatus.DENIED.getValue());
            }
        });
        return data;
    }

    @Override
    public LiveData<ApiError> getErrorObserver() {
        return null;
    }

    public LiveData<String> addUser(String phone, String token) {
        final MutableLiveData<String> data = new MutableLiveData<>();
        apiService.addUser(phone, token).enqueue(new Callback<UserStatusModel>() {
            @Override
            public void onResponse(@NonNull Call<UserStatusModel> call,
                                   @NonNull Response<UserStatusModel> response) {
                if (response.isSuccessful()) {
                        data.setValue("success");
                }else {

                    data.setValue(response.toString() + response.body().isSuccess());
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserStatusModel> call, @NonNull Throwable t) {
                data.setValue(t.getMessage());
            }
        });
        return data;
    }

    public void sendDate(String token, int fullPrice, String title, String body) {
        final MutableLiveData<String> data = new MutableLiveData<>();
        apiService.sendDate(token, fullPrice, title,  body).enqueue(new Callback<UserStatusModel>() {
            @Override
            public void onResponse(@NonNull Call<UserStatusModel> call,
                                   @NonNull Response<UserStatusModel> response) {
                if (response.isSuccessful()) {
                    data.setValue("success");
                }else {

                    data.setValue(response.toString() + response.body().isSuccess());
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserStatusModel> call, @NonNull Throwable t) {
                data.setValue(t.getMessage());
            }
        });
//        return data;
    }
}
