package com.example.yang.mytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yang.mytest.Bean.Utils;
import com.example.yang.mytest.Http.HttpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 欢迎页
 */
public class MainActivity extends Activity {

    private static String TAG = "MainActivity";
    @BindView(R.id.img)
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Utils.fullScreen(this);
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
