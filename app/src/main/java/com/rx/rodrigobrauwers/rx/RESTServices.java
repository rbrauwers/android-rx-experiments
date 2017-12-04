package com.rx.rodrigobrauwers.rx;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by rodrigobrauwers on 01/12/17.
 */

public class RESTServices {

    private BreedsService breedsService;

    public RESTServices() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        breedsService = retrofit.create(BreedsService.class);
    }

    public BreedsService getBreedsService() {
        return breedsService;
    }

    public interface BreedsService {
        @GET("breeds/list/all")
        Observable<Object> breeds();

        @GET("breeds/list")
        Observable<Object> masterBreeds();
    }
}
