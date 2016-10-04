package com.example.thomasskogemann.flashcard.data.model;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Thomas Skogemann on 04-10-2016.
 */
public class ApiController {

    private OkHttpClient client;
    private static ApiController instance;

    public static ApiController getInstance() {
        if (instance == null) {
            instance = new ApiController();
        }
        return instance;
    }

    public OkHttpClient getClient(){
        return client;
    }

    public ApiController() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(logging)
                .build();
    }


    private class HeaderInterceptor implements Interceptor {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Request originalRequest = chain.request();

            okhttp3.Request.Builder builder = originalRequest.newBuilder()
                    .addHeader("Accept-Language", "da-DK")
                    .addHeader("Accept", "application/json");


            okhttp3.Request newRequest = builder.build();

            return chain.proceed(newRequest);

        }
    }
}
