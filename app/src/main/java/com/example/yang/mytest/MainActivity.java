package com.example.yang.mytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.transition.Explode;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yang.mytest.Http.HttpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @BindView(R.id.img)
    ImageView img;

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toast.makeText(this, "欢迎使用智能农业\nip地址为" + HttpUtils.myUrl + "\n可前往设置更改", Toast.LENGTH_LONG).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, TabLayout.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }

}
