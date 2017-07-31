package com.example.haidarafif.crudretrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HaidarAfif on 31/07/17.
 */

public class CrudService {

    private RegisterApi registerApi;

    public CrudService () {

        OkHttpClient.Builder okhttpBuilder = new OkHttpClient().newBuilder();
        okhttpBuilder.connectTimeout(60, TimeUnit.SECONDS);
        okhttpBuilder.writeTimeout(60, TimeUnit.SECONDS);
        okhttpBuilder.readTimeout(60, TimeUnit.SECONDS);
        okhttpBuilder.retryOnConnectionFailure(true);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okhttpBuilder.addInterceptor(interceptor);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .client(okhttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        registerApi = retrofit.create(RegisterApi.class);

    }

    public void tambahMahasiswa(String npm, String nama, String kelas, String sesi, Callback callback){
        registerApi.daftar(npm,nama,kelas,sesi).enqueue(callback);
    }

    public void updateMahasiswa(String npm,String nama,String kelas, String sesi, Callback callback){
        registerApi.ubah(npm,nama,kelas,sesi).enqueue(callback);
    }

    public void hapusMahasiswa(String npm, Callback callback){
        registerApi.hapus(npm).enqueue(callback);
    }


    public void tampilMahasiswa( Callback callback){
        registerApi.view().enqueue(callback);
    }
    public void cariMahasiswa(String search, Callback callback){
        registerApi.search(search).enqueue(callback);
    }





}
