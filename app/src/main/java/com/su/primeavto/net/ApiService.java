package com.su.primeavto.net;

import com.su.primeavto.model.ResponseModel;
import com.su.primeavto.model.UserStatusModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {

    String KEY_TOKEN = "token";
    String PATH_TOKEN = "{token}/";


    @POST()
    Call<ResponseBody> sendMessage(
            @Url String url,
            @Query("login") String login,
            @Query("password") String password,
            @Query("phone") String phone,
            @Query("text") String text
    );

    @POST("local/php_interface/include/add_user.php")
    Call<UserStatusModel> addUser(@Query("phone") String phone,
                                  @Query(KEY_TOKEN) String token);

    @POST("local/php_interface/include/get_user_status.php")
    Call<UserStatusModel> getUserStatus(@Query("phone") String phone,
                                        @Query(KEY_TOKEN) String token);

    @GET("local/php_interface/include/json_product.php/" + PATH_TOKEN)
    Call<ResponseModel> getCars(@Query(KEY_TOKEN) String token);

    @Multipart
    @POST("local/php_interface/include/sendEmail.php")
    Call<UserStatusModel> getSendDocs(
            @Query(KEY_TOKEN) String token,
            @Query("PhoneNumber") String phone,
            @Part List<MultipartBody.Part> images
    );

    @POST("local/php_interface/include/cloud.php")
    Call<UserStatusModel> sendDate(String token, int fullPrice, String title, String body);
}
