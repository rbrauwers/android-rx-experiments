package com.rx.rodrigobrauwers.rx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ServicesActivity extends AppCompatActivity {

    private RESTServices services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        ButterKnife.bind(this);
        services = new RESTServices();
    }

    @OnClick(R.id.button)
    public void buttonClick() {
        services.getBreedsService()
                .breeds()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object -> Log.d("DEBUG", String.format("Breeds response: %s", object)));
    }

    @OnClick(R.id.button2)
    public void button2Click() {
        services.getBreedsService()
                .masterBreeds()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        object -> Log.d("DEBUG", String.format("Master breeds response: %s", object)),
                        error -> Log.d("DEBUG", String.format("Master breeds error: %s", error)));
    }

    @OnClick(R.id.button3)
    public void button3Click() {
        Observable.zip(
                services.getBreedsService().breeds(), services.getBreedsService().masterBreeds(), (breeds, masterBreeds) -> {
                    Log.d("DEBUG", String.format("Breeds response: %s", breeds));
                    Log.d("DEBUG", String.format("Master breeds response: %s", masterBreeds));
                    return "kine é show";
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object -> Log.d("DEBUG", String.format("Processed result: %s", object)));
    }

    @OnClick(R.id.button4)
    public void button4Click() {
        services.getBreedsService()
                .breeds()
                .flatMap(
                    breeds -> {
                        Log.d("DEBUG", String.format("Breeds response: %s", breeds));
                        return services.getBreedsService().masterBreeds();
                    })
                /*
                .onErrorReturn(
                        e -> {
                            Log.d("ERROR 1", String.format("error: %s", e));
                            return Observable.error(e);
                        })
                */
                /*.onErrorResumeNext(
                    e -> {
                        Log.d("ERROR 1", String.format("error: %s", e));
                        return Observable.error(e);
                    })*/
                /*
                .onErrorResumeNext(
                    e -> {
                        Log.d("ERROR 1", String.format("error: %s", e));
                        return Observable.empty();
                    })
                */
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(error -> Log.d("ERROR 2", String.format("error: %s", error)))
                .onErrorResumeNext(Observable.empty())
                .subscribe(object -> Log.d("DEBUG", String.format("Response: %s", object)), error -> Log.d("ERROR 3", String.format("error: %s", error)));
    }
}
