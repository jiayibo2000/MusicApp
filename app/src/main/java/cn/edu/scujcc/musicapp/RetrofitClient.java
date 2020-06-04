package cn.edu.scujcc.musicapp;

import com.squareup.moshi.Moshi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    public static Retrofit getRetrofit(){
        if (retrofit == null){

            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Authlnterceptor()).build();
            retrofit = new Retrofit.Builder().baseUrl("http://47.112.225.217:8080").addConverterFactory(MoshiConverterFactory.create()).build();
        }
        return retrofit;
    }
}
