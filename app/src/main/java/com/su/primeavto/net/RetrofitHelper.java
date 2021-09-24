package com.su.primeavto.net;

import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.su.primeavto.BuildConfig.BASE_URL;

public class RetrofitHelper {

    private static final ApiService apiService = create();


    public static ApiService get() {
        return apiService;
    }


    private static ApiService create() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                .client(createHttpClient())
                .build()
                .create(ApiService.class);
    }


    public static OkHttpClient createHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getLogInterceptor())
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .build();
    }


    private static Interceptor getLogInterceptor() {
        return chain -> chain.proceed(chain.request().newBuilder().build());
    }

}
