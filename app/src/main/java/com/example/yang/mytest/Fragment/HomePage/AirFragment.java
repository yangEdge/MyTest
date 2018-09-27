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

import com.example.yang.mytest.Bean.GetConfig;
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
 */
public class AirFragment extends Fragment {
    private static String TAG = "AirFragment";

    @BindView(R.id.airTemperature_details)
    TextView airTemperature_details;
    @BindView(R.id.airTemperature_minAirTemperature)
    TextView airTemperature_minAirTemperature;
    @BindView(R.id.airTemperature_maxAirTemperature)
    TextView airTemperature_maxAirTemperature;
    @BindView(R.id.airHumidity_details)
    TextView airHumidity_details;
    @BindView(R.id.airHumidity_minAirHumidity)
    TextView airHumidity_minAirHumidity;
    @BindView(R.id.airHumidity_maxAirHumidity)
    TextView airHumidity_maxAirHumidity;
    @BindView(R.id.air_fengshan)
    ImageView air_fengshan;
    @BindView(R.id.air_guangzhao)
    ImageView air_guangzhao;
    @BindView(R.id.air_shui)
    ImageView air_shui;
    @BindView(R.id.air_baojing)
    ImageView air_baojing;

    public AirFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_air, container, false);
        ButterKnife.bind(this, view);

        getFocus(view);
        getSensor();
        getConfig();
        HttpUtils.getContorllerStatus(air_fengshan, air_guangzhao, air_shui, air_baojing);
        return view;
    }


    private void getSensor() {
        Call<ZhuYeReception> call = HttpUtils.request().getSensor();
        call.enqueue(new Callback<ZhuYeReception>() {

            @Override
            public void onResponse(Call<ZhuYeReception> call, Response<ZhuYeReception> response) {
                ZhuYeReception zhuYeReception = response.body();
                airTemperature_details.setText(zhuYeReception.getAirTemperature() + "");
                airHumidity_details.setText(zhuYeReception.getAirHumidity() + "");
            }

            @Override
            public void onFailure(Call<ZhuYeReception> call, Throwable throwable) {
                Log.e(TAG, "请求失败");
                Log.e(TAG, throwable.getMessage());
            }
        });
    }

    private void getConfig() {
        Call<GetConfig> call = HttpUtils.request().getConfig();
        call.enqueue(new Callback<GetConfig>() {

            @Override
            public void onResponse(Call<GetConfig> call, Response<GetConfig> response) {
                GetConfig getConfig = response.body();
                airTemperature_minAirTemperature.setText(getConfig.getMinAirTemperature() + "");
                airTemperature_maxAirTemperature.setText(getConfig.getMaxAirTemperature() + "");
                airHumidity_minAirHumidity.setText(getConfig.getMinAirHumidity() + "");
                airHumidity_maxAirHumidity.setText(getConfig.getMaxAirHumidity() + "");
            }

            @Override
            public void onFailure(Call<GetConfig> call, Throwable throwable) {
                Log.e(TAG, "请求失败");
                Log.e(TAG, throwable.getMessage());
            }
        });
    }

    @OnClick(value = {R.id.img_fanhui, R.id.air_fengshan, R.id.air_guangzhao, R.id.air_shui, R.id.air_baojing})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_fanhui:
                back();
                break;
            case R.id.air_fengshan:
                HttpUtils.controlBlower(air_fengshan);
                break;
            case R.id.air_guangzhao:
                HttpUtils.controlRoadlamp(air_guangzhao);
                break;
            case R.id.air_shui:
                HttpUtils.controlWaterPump(air_shui);
                break;
            case R.id.air_baojing:
                HttpUtils.controlBuzzer(air_baojing);
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
