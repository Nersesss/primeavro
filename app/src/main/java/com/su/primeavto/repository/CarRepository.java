package com.su.primeavto.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.su.primeavto.ErrorMessenger;
import com.su.primeavto.model.AutoModel;
import com.su.primeavto.model.ResponseModel;
import com.su.primeavto.model.UserStatusModel;
import com.su.primeavto.net.ApiError;
import com.su.primeavto.net.ApiService;
import com.su.primeavto.net.RetrofitHelper;
import com.su.primeavto.util.RequestUtil;

import java.io.File;
import java.io.IOException;
import java.net.HttpCookie;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Part;

import static com.su.primeavto.view.fragment.CarsFragment.TAG;

public class CarRepository implements ErrorMessenger {

    private static final String KEY_LIST_IMAGES = "data";
    private final MutableLiveData<ApiError> apiError = new MutableLiveData<>();
    public static final ApiService apiService = RetrofitHelper.get();
    private static final CarRepository INSTANCE = new CarRepository();


    public static CarRepository getInstance() {
        return INSTANCE;
    }


    @Override
    public LiveData<ApiError> getErrorObserver() {
        return null;
    }

    public LiveData<List<AutoModel>> getCars(String token) {
        final MutableLiveData<List<AutoModel>> data = new MutableLiveData<>();

        apiService.getCars(token).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call,
                                   @NonNull Response<ResponseModel> response) {
                if (response.body() != null) {
                    data.setValue(response.body().getAutos());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                apiError.setValue(new ApiError(t.getMessage()));
            }
        });

        return data;
    }

    public LiveData<Boolean> sendDocs(String token, String phoneNumber, List<File> files) {
        final MutableLiveData<Boolean> data = new MutableLiveData<>();
        List<MultipartBody.Part> imageList = RequestUtil.MultipartBodyFactory.create(files, KEY_LIST_IMAGES,
                RequestUtil.MediaTypes.MEDIA_TYPE_IMAGE_JPEG);
        apiService.getSendDocs(token, phoneNumber, imageList).enqueue(new Callback<UserStatusModel>() {
            @Override
            public void onResponse(@NonNull Call<UserStatusModel> call,
                                   @NonNull Response<UserStatusModel> response) {

                data.setValue(response.body() != null && response.isSuccessful() && response.body().isSuccess());
            }

            @Override
            public void onFailure(@NonNull Call<UserStatusModel> call, @NonNull Throwable t) {
                data.setValue(false);
            }
        });

        return data;
    }
}
