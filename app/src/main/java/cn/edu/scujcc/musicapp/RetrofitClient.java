package cn.edu.scujcc.musicapp;



import com.squareup.moshi.Moshi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;


public class RetrofitClient {
    private static Retrofit retrofit = null;
    public static Retrofit getRetrofit(){
        if (retrofit == null){



            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                    .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                    .readTimeout(5, TimeUnit.MINUTES) // read timeout
                    //.addInterceptor(new AuthInterceptor())
                    .build();





            retrofit = new Retrofit.Builder().baseUrl("http://47.112.225.217:8080").addConverterFactory(MoshiConverterFactory.create()).callFactory(client).build();
        }
        return retrofit;
    }
}
