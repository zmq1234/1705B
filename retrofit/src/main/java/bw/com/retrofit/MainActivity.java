package bw.com.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import bw.com.retrofit.api.Api;
import bw.com.retrofit.api.ApiService;
import bw.com.retrofit.entity.LoginUserEntity;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_login)
    Button btnLogin;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void btnloginClick() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();

        //第一步:第一类的作用,统筹规划
        Retrofit retrofit = new Retrofit.Builder()//构建这模式
                 .addConverterFactory(GsonConverterFactory.create())
                 .baseUrl(Api.BASE_URL)
                 .client(okHttpClient)//使用okhttp的所有功能,配置okhttp
                 .build();
        //第二步:得到请求的url,比如登录url,初始化接口管理类
        ApiService apiService = retrofit.create(ApiService.class);

        apiService.login(Api.LOGIN_URL,"17319392086","123").enqueue(new retrofit2.Callback<LoginUserEntity>(){
            @Override
            public void onResponse(Call<LoginUserEntity> call, Response<LoginUserEntity> response) {
                //回调方法的线程,是ui线程.okhttp是子线程需要handler切换线程

                LoginUserEntity loginUserEntity = response.body();//对象
                String phone = loginUserEntity.result.phone;

                Log.e("qqq",phone);
            }

            @Override
            public void onFailure(Call<LoginUserEntity> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
