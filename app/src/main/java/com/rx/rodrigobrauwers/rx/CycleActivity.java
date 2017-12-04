package com.rx.rodrigobrauwers.rx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.trello.rxlifecycle2.components.RxActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CycleActivity extends RxActivity {

    @BindView(R.id.textView)
    TextView textView;

    private Observable<Integer> observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cycle);
        ButterKnife.bind(this);

        observable = Observable.range(1, 1000)
                .concatMap(i -> Observable.just(i).delay(1000, TimeUnit.MILLISECONDS))
                .doOnNext(i -> Log.d("DEBUG", String.format("emitting: %d", i)));

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(value -> textView.setText(value.toString()));
    }
}
