package com.rx.rodrigobrauwers.rx;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class PermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemission);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void buttonClick() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.SEND_SMS, Manifest.permission.CAMERA)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(granted -> Toast.makeText(this, String.format("Granted: %b", granted), Toast.LENGTH_SHORT).show());
    }
}
