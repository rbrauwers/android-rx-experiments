package com.rx.rodrigobrauwers.rx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void buttonClick() {
        Intent intent = new Intent(this, TextActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button2)
    public void buttonClick2() {
        Intent intent = new Intent(this, CycleActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button3)
    public void buttonClick3() {
        Intent intent = new Intent(this, PermissionActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button4)
    public void buttonClick4() {
        //Intent intent = new Intent(this, ServicesActivity.class);
        //startActivity(intent);

        //double distance = distance(1, 2, 3, 4, 5, 6);
        //Log.d("quadrado", String.format("%f", distance));

        Log.d("pal", String.format("%b", WordUtils.isAlmostPalindrome("abccba")));
        Log.d("pal", String.format("%b", WordUtils.isAlmostPalindrome("abccbx")));
        Log.d("pal", String.format("%b", WordUtils.isAlmostPalindrome("abccfg")));
    }


}
