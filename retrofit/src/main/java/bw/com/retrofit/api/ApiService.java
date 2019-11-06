package bw.com.retrofit.api;

import android.util.Log;

import bw.com.retrofit.entity.LoginUserEntity;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Time:2019/11/6
 * Author:周盟棋
 * Description:
 */
public interface ApiService {

    @POST
    @FormUrlEncoded//请求参数类型
    Call<LoginUserEntity> login(@Url String url, @Field("phone")String phoneNum,@Field("pwd")String password);

}
