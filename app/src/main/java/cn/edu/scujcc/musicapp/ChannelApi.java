package cn.edu.scujcc.musicapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public  interface  ChannelApi {
    @GET("/user/login/{username}/{password}")
     Call<Integer> login(@Path("username") String username, @Path("password") String password);
}
