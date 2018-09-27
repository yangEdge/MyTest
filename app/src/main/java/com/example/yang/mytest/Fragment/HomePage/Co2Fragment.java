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
import com.example.yang.mytest.Http.HttpUtils;
import com.example.yang.mytest.Bean.ZhuYeReception;
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
 * CO2
 */
public class Co2Fragment extends Fragment {
    private static String TAG = "Co2Fragment";

    @BindView(R.id.co2_details)
    TextView co2_details;
    @BindView(R.id.co2_minCo2)
    TextView co2_minCo2;
    @BindView(R.id.co2_maxCo2)
    TextView co2_maxCo2;
    @BindView(R.id.co2_fengshan)
    ImageView co2_fengshan;
    @BindView(R.id.co2_baojing)
    ImageView co2_baojing;


    public Co2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_co2, container, false);
        ButterKnife.bind(this, view);

        getFocus(view);
        getSensor();
        getConfig();
        HttpUtils.getContorllerStatus(co2_fengshan, null, null, co2_baojing);
        return view;
    }

    /**
     * CO2浓度查询
     */
    private void getSensor() {
        Call<ZhuYeReception> call = HttpUtils.request().getSensor();
        call.enqueue(new Callback<ZhuYeReception>() {

            @Override
            public void onResponse(Call<ZhuYeReception> call, Response<ZhuYeReception> response) {
                ZhuYeReception zhuYeReception = response.body();
                co2_details.setText(zhuYeReception.getCo2() + "");
            }

            @Override
            public void onFailure(Call<ZhuYeReception> call, Throwable throwable) {
                Log.e(TAG, "请求失败");
                Log.e(TAG, throwable.getMessage());
            }
        });
    }

    /**
     * CO2指数设定值查询
     */
    private void getConfig() {
        Call<Config> call = HttpUtils.request().getConfig();
        call.enqueue(new Callback<Config>() {

            @Override
            public void onResponse(Call<Config> call, Response<Config> response) {
                Config getConfig = response.body();
                co2_minCo2.setText(getConfig.getMinCo2() + "");
                co2_maxCo2.setText(getConfig.getMaxCo2() + "");
            }

            @Override
            public void onFailure(Call<Config> call, Throwable throwable) {
                Log.e(TAG, "请求失败");
                Log.e(TAG, throwable.getMessage());
            }
        });
    }

    @OnClick(value = {R.id.img_fanhui, R.id.co2_fengshan, R.id.co2_baojing})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_fanhui:
                back();
                break;
            case R.id.co2_fengshan:
                HttpUtils.controlBlower(co2_fengshan);
                break;
            case R.id.co2_baojing:
                HttpUtils.controlBuzzer(co2_baojing);
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