package com.example.myapplication.network;

import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.FactsPreferences;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder addAuthorizationToken = request.newBuilder()
                        .addHeader("Authorization", FactsPreferences.getApiToken());
                return chain.proceed(addAuthorizationToken.build());
            }
        });
        builder.addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder addAcceptType = request.newBuilder()
                        .addHeader("Accept", Constants.ACCEPT_TYPE);
                return chain.proceed(addAcceptType.build());
            }
        });
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
