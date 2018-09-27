package com.example.yang.mytest.Fragment.HomePage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yang.mytest.Bean.Config;
import com.example.yang.mytest.Bean.ZhuYeReception;
import com.example.yang.mytest.Http.HttpUtils;
import com.example.yang.mytest.R;
import com.example.yang.mytest.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * 土壤
 */
public class SoilFragment extends Fragment {
    private static String TAG = "SoilFragment";

    @BindView(R.id.soilTemperature_details)
    TextView soilTemperature_details;
    @BindView(R.id.soilTemperature_minSoilTemperature)
    TextView soilTemperature_minSoilTemperature;
    @BindView(R.id.soilTemperature_maxSoilTemperature)
    TextView soilTemperature_maxSoilTemperature;
    @BindView(R.id.soilHumidity_details)
    TextView soilHumidity_details;
    @BindView(R.id.soilHumidity_minSoilHumidity)
    TextView soilHumidity_minSoilHumidity;
    @BindView(R.id.soilHumidity_maxSoilHumidity)
    TextView soilHumidity_maxSoilHumidity;
    @BindView(R.id.soil_guangzhao)
    ImageView soil_guangzhao;
    @BindView(R.id.soil_shui)
    ImageView soil_shui;
    @BindView(R.id.soil_baojing)
    ImageView soil_baojing;

    public SoilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_soil, container, false);
        ButterKnife.bind(this, view);

        getFocus(view);
        getSensor();
        getConfig();
        HttpUtils.getContorllerStatus(null, soil_guangzhao, soil_shui, soil_baojing);
        return view;
    }

    /**
     * 土壤指数情况查询
     */
    private void getSensor() {
        Call<ZhuYeReception> call = HttpUtils.request().getSensor();
        call.enqueue(new Callback<ZhuYeReception>() {

            @Override
            public void onResponse(Call<ZhuYeReception> call, Response<ZhuYeReception> response) {
                ZhuYeReception zhuYeReception = response.body();
                soilTemperature_details.setText(zhuYeReception.getSoilTemperature() + "");
                soilHumidity_details.setText(zhuYeReception.getSoilHumidity() + "");
            }

            @Override
            public void onFailure(Call<ZhuYeReception> call, Throwable throwable) {
                Log.e(TAG, "请求失败");
                Log.e(TAG, throwable.getMessage());
            }
        });
    }

    /**
     * 土壤指数设定值查询
     */
    private void getConfig() {
        Call<Config> call = HttpUtils.request().getConfig();
        call.enqueue(new Callback<Config>() {

            @Override
            public void onResponse(Call<Config> call, Response<Config> response) {
                Config getConfig = response.body();
                soilTemperature_minSoilTemperature.setText(getConfig.getMinSoilTemperature() + "");
                soilTemperature_maxSoilTemperature.setText(getConfig.getMaxSoilTemperature() + "");
                soilHumidity_minSoilHumidity.setText(getConfig.getMinSoilHumidity() + "");
                soilHumidity_maxSoilHumidity.setText(getConfig.getMaxSoilHumidity() + "");
            }

            @Override
            public void onFailure(Call<Config> call, Throwable throwable) {
                Log.e(TAG, "请求失败");
                Log.e(TAG, throwable.getMessage());
            }
        });
    }

    @OnClick(value = {R.id.img_fanhui, R.id.soil_guangzhao, R.id.soil_shui, R.id.soil_baojing})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_fanhui:
                back();
                break;
            case R.id.soil_guangzhao:
                HttpUtils.controlRoadlamp(soil_guangzhao);
                break;
            case R.id.soil_shui:
                HttpUtils.controlWaterPump(soil_shui);
                break;
            case R.id.soil_baojing:
                HttpUtils.controlBuzzer(soil_baojing);
                break;
            default:
        }
    }

    private void getFocus(View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN &&
                        keyCode == KeyEvent.KEYCODE_BACK) {
                    back();
                }
                return false;
            }
        });
    }

    private void back() {
        Intent intent = new Intent(getActivity(), TabLayout.class);
        startActivity(intent);
        getActivity().onBackPressed();
    }
}
