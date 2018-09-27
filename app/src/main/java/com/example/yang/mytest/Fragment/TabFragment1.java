package com.example.yang.mytest.Fragment;


import android.support.v4.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yang.mytest.Adapter.UltraPagerAdapter;
import com.example.yang.mytest.Fragment.HomePage.AirFragment;
import com.example.yang.mytest.Fragment.HomePage.Co2Fragment;
import com.example.yang.mytest.Fragment.HomePage.LightFragment;
import com.example.yang.mytest.Fragment.HomePage.SoilFragment;
import com.example.yang.mytest.Http.BaseHttp;
import com.example.yang.mytest.Bean.ZhuYeReception;
import com.example.yang.mytest.R;
import com.tmall.ultraviewpager.UltraViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabFragment1 extends Fragment {
    private static String TAG = "TabFragment1";

    //指数数值
    @BindView(R.id.co2)
    TextView tv_co2;
    @BindView(R.id.light)
    TextView tv_light;
    @BindView(R.id.soilTemperature)
    TextView tv_soilTemperature;
    @BindView(R.id.soilHumidity)
    TextView tv_soilHumidity;
    @BindView(R.id.airTemperature)
    TextView tv_airTemperature;
    @BindView(R.id.airHumidity)
    TextView tv_airHumidity;

    //设定值
    @BindView(R.id.sheding_co2)
    TextView sheding_co2;
    @BindView(R.id.sheding_light)
    TextView sheding_light;
    @BindView(R.id.sheding_soilTemperature)
    TextView sheding_soilTemperature;
    @BindView(R.id.sheding_soilHumidity)
    TextView sheding_soilHumidity;
    @BindView(R.id.sheding_airTemperature)
    TextView sheding_airTemperature;
    @BindView(R.id.sheding_airHumidity)
    TextView sheding_airHumidity;

    @BindView(R.id.myUlViewPager)
    UltraViewPager mUltraViewPager;
    private int[] imageArr = {R.drawable.bana1, R.drawable.bana2, R.drawable.bana3};
    private UltraPagerAdapter mAdapter;
    public boolean flag = true;
    private Thread thread;

    public TabFragment1() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart---------");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy---------");
        flag = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_fragment1, container, false);
        ButterKnife.bind(this, view);

        viewPager(view);
        flag = true;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    request();
                    try {
                        Thread.sleep(2000);
                        Log.e(TAG, thread.getName()+"**********");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

        return view;
    }

    private void request() {
        Call<ZhuYeReception> call = BaseHttp.request().getSensor();
        call.enqueue(new Callback<ZhuYeReception>() {

            @Override
            public void onResponse(Call<ZhuYeReception> call, Response<ZhuYeReception> response) {
                Log.e(TAG, "request请求成功");
                ZhuYeReception zhuYeReception = response.body();
                tv_co2.setText(zhuYeReception.getCo2() + "");
                tv_light.setText(zhuYeReception.getLight() + "");
                tv_soilTemperature.setText(zhuYeReception.getSoilTemperature() + "");
                tv_soilHumidity.setText(zhuYeReception.getSoilHumidity() + "");
                tv_airTemperature.setText(zhuYeReception.getAirTemperature() + "");
                tv_airHumidity.setText(zhuYeReception.getAirHumidity() + "");
            }

            @Override
            public void onFailure(Call<ZhuYeReception> call, Throwable throwable) {
                Log.e(TAG, "请求失败");
                Log.e(TAG, throwable.getMessage());
            }
        });
    }

    @OnClick(value = {R.id.f1_card1, R.id.f1_card2, R.id.f1_card3, R.id.f1_card4})
    public void onClick(View v) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        switch (v.getId()) {
            case R.id.f1_card1:
                Fragment co2Fragment = new Co2Fragment();
                fm.beginTransaction().replace(R.id.tab, co2Fragment).commit();

                break;
            case R.id.f1_card2:
                Fragment lightFragment = new LightFragment();
                fm.beginTransaction().replace(R.id.tab, lightFragment).commit();
                break;
            case R.id.f1_card3:
                Fragment soilFragment = new SoilFragment();
                fm.beginTransaction().replace(R.id.tab, soilFragment).commit();
                break;
            case R.id.f1_card4:
                Fragment airFragment = new AirFragment();
                fm.beginTransaction().replace(R.id.tab, airFragment).commit();
                break;
            default:
        }
    }

    private void viewPager(View view) {
        mUltraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        mAdapter = new UltraPagerAdapter(view.getContext(), imageArr);
        mUltraViewPager.setAdapter(mAdapter);

        //内置indicator初始化
        mUltraViewPager.setMultiScreen(1.0f);
        mUltraViewPager.setRatio(2.4f);
        mUltraViewPager.initIndicator();
        mUltraViewPager.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.GREEN)
                .setNormalColor(Color.WHITE)
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
        //设置indicator对齐方式
        mUltraViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        mUltraViewPager.getIndicator().setMargin(0, 0, 0, 20);
        //构造indicator,绑定到UltraViewPager
        mUltraViewPager.getIndicator().build();

        //设定页面循环播放
        mUltraViewPager.setInfiniteLoop(true);
        //设定页面自动切换  间隔2秒
        mUltraViewPager.setAutoScroll(2000);
    }
}