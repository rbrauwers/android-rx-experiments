package com.rx.rodrigobrauwers.rx;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.editText)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_text);
        ButterKnife.bind(this);

        RxTextView.textChanges(editText).subscribe(charSequence -> textView.setText(charSequence));
    }
}
