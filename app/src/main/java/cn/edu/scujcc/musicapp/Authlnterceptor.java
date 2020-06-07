package cn.edu.scujcc.musicapp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class Authlnterceptor implements Interceptor {
    MyPreference prefs = MyPreference.getInstance();
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request request = originalRequest.newBuilder().addHeader("token",prefs.currentToken()).build();
        return  chain.proceed(request);
    }
}
