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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yang.mytest.Adapter.UltraPagerAdapter;
import com.example.yang.mytest.Bean.Config;
import com.example.yang.mytest.Fragment.HomePage.AirFragment;
import com.example.yang.mytest.Fragment.HomePage.Co2Fragment;
import com.example.yang.mytest.Fragment.HomePage.LightFragment;
import com.example.yang.mytest.Fragment.HomePage.SoilFragment;
import com.example.yang.mytest.Http.HttpUtils;
import com.example.yang.mytest.Bean.ZhuYeReception;
import com.example.yang.mytest.R;
import com.tmall.ultraviewpager.UltraViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 首页
 */
public class HomeTabFragment extends Fragment {
    private static String TAG = "HomeTabFragment";

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
    @BindView(R.id.f1_minCo2)
    TextView f1_minCo2;
    @BindView(R.id.f1_maxCo2)
    TextView f1_maxCo2;
    @BindView(R.id.f1_minLight)
    TextView f1_minLight;
    @BindView(R.id.f1_maxLight)
    TextView f1_maxLight;
    @BindView(R.id.f1_minAirTemperature)
    TextView f1_minAirTemperature;
    @BindView(R.id.f1_maxAirTemperature)
    TextView f1_maxAirTemperature;
    @BindView(R.id.f1_minAirHumidity)
    TextView f1_minAirHumidity;
    @BindView(R.id.f1_maxAirHumidity)
    TextView f1_maxAirHumidity;
    @BindView(R.id.f1_minSoilTemperature)
    TextView f1_minSoilTemperature;
    @BindView(R.id.f1_maxSoilTemperature)
    TextView f1_maxSoilTemperature;
    @BindView(R.id.f1_minSoilHumidity)
    TextView f1_minSoilHumidity;
    @BindView(R.id.f1_maxSoilHumidity)
    TextView f1_maxSoilHumidity;

    //警告提示
    @BindView(R.id.img_p_co2)
    ImageView img_p_co2;
    @BindView(R.id.img_p_light)
    ImageView img_p_light;
    @BindView(R.id.img_p_soil)
    ImageView img_p_soil;
    @BindView(R.id.img_p_air)
    ImageView img_p_air;

    @BindView(R.id.myUlViewPager)
    UltraViewPager mUltraViewPager;

    private int[] imageArr = {R.drawable.bana1, R.drawable.bana2, R.drawable.bana3};
    private UltraPagerAdapter mAdapter;
    public boolean flag = true;
    private Thread thread;

    private static int co2 = 0;
    private static int light = 0;
    private static int soilTemperature = 0;
    private static int soilHumidity = 0;
    private static int airTemperature = 0;
    private static int airHumidity = 0;

    public HomeTabFragment() {
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
                    getRange();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

        return view;
    }

    /**
     * 各个指数情况
     */
    private void request() {
        Call<ZhuYeReception> call = HttpUtils.request().getSensor();
        call.enqueue(new Callback<ZhuYeReception>() {

            @Override
            public void onResponse(Call<ZhuYeReception> call, Response<ZhuYeReception> response) {
                ZhuYeReception zhuYeReception = response.body();
                tv_co2.setText((co2 = zhuYeReception.getCo2()) + "");
                tv_light.setText((light = zhuYeReception.getLight()) + "");
                tv_soilTemperature.setText((soilHumidity = zhuYeReception.getSoilTemperature()) + "");
                tv_soilHumidity.setText((soilHumidity = zhuYeReception.getSoilHumidity()) + "");
                tv_airTemperature.setText((airTemperature = zhuYeReception.getAirTemperature()) + "");
                tv_airHumidity.setText((airHumidity = zhuYeReception.getAirHumidity()) + "");
            }

            @Override
            public void onFailure(Call<ZhuYeReception> call, Throwable throwable) {
                Log.e(TAG, "getSensor请求失败");
                Log.e(TAG, throwable.getMessage());
            }
        });
    }

    /**
     * 设定值情况
     */
    private void getRange() {
        Call<Config> getRange = HttpUtils.request().getConfig();
        getRange.enqueue(new Callback<Config>() {

            @Override
            public void onResponse(Call<Config> call, Response<Config> response) {
                Config getConfig = response.body();
                f1_minCo2.setText(getConfig.getMinCo2() + "");
                f1_maxCo2.setText(getConfig.getMaxCo2() + "");
                f1_minLight.setText(getConfig.getMinLight() + "");
                f1_maxLight.setText(getConfig.getMaxLight() + "");
                f1_minSoilTemperature.setText(getConfig.getMinSoilTemperature() + "");
                f1_maxSoilTemperature.setText(getConfig.getMaxSoilTemperature() + "");
                f1_minSoilHumidity.setText(getConfig.getMinSoilHumidity() + "");
                f1_maxSoilHumidity.setText(getConfig.getMaxSoilHumidity() + "");
                f1_minAirTemperature.setText(getConfig.getMinAirTemperature() + "");
                f1_maxAirTemperature.setText(getConfig.getMaxAirTemperature() + "");
                f1_minAirHumidity.setText(getConfig.getMinAirHumidity() + "");
                f1_maxAirHumidity.setText(getConfig.getMaxAirHumidity() + "");

                judgement(getConfig);
            }

            @Override
            public void onFailure(Call<Config> call, Throwable throwable) {
                Log.e(TAG, "getRange请求失败");
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

    /**
     * viewPager
     * @param view
     */
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

    /**
     * 指数比较，设定警告标志
     * @param getConfig
     */
    private void judgement(Config getConfig) {
        if (co2 > getConfig.getMinCo2() + 10 && co2 < getConfig.getMaxCo2() - 10)
            img_p_co2.setImageResource(R.mipmap.p1);
        else if (co2 > getConfig.getMinCo2() && co2 < getConfig.getMaxCo2())
            img_p_co2.setImageResource(R.mipmap.p2);
        else
            img_p_co2.setImageResource(R.mipmap.p3);

        if (light > getConfig.getMinLight() + 10 && light < getConfig.getMaxLight() - 10)
            img_p_light.setImageResource(R.mipmap.p1);
        else if (light > getConfig.getMinCo2() && light < getConfig.getMaxLight())
            img_p_light.setImageResource(R.mipmap.p2);
        else
            img_p_light.setImageResource(R.mipmap.p3);

        if (soilTemperature > getConfig.getMinSoilTemperature() + 10 && soilTemperature < getConfig.getMaxSoilTemperature() - 10 ||
                soilHumidity > getConfig.getMinSoilHumidity() + 20 && soilHumidity < getConfig.getMaxSoilHumidity() - 20)
            img_p_soil.setImageResource(R.mipmap.p1);
        else if (soilTemperature > getConfig.getMinSoilTemperature() && soilTemperature < getConfig.getMaxSoilTemperature() ||
                soilHumidity > getConfig.getMinSoilHumidity() && soilHumidity < getConfig.getMaxSoilHumidity())
            img_p_soil.setImageResource(R.mipmap.p2);
        else
            img_p_soil.setImageResource(R.mipmap.p3);

        if (airTemperature > getConfig.getMinAirTemperature() + 10 && airTemperature < getConfig.getMaxAirTemperature() - 10 ||
                airHumidity > getConfig.getMinAirHumidity() + 20 && airHumidity < getConfig.getMaxAirHumidity() - 20)
            img_p_air.setImageResource(R.mipmap.p1);
        else if (airTemperature > getConfig.getMinAirTemperature() && airTemperature < getConfig.getMaxAirTemperature() ||
                airHumidity > getConfig.getMinAirHumidity() && airHumidity < getConfig.getMaxAirHumidity())
            img_p_air.setImageResource(R.mipmap.p2);
        else
            img_p_air.setImageResource(R.mipmap.p3);
    }
}