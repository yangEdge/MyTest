package com.example.yang.mytest.Fragment.SetUp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yang.mytest.Bean.Config;
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
 * 设定各个参数的最大最小值
 */
public class ShouDongKongZhi extends Fragment {
    private static String TAG = "ShouDongKongZhi";

    @BindView(R.id.minCo2)
    EditText minCo2;
    @BindView(R.id.maxCo2)
    EditText maxCo2;
    @BindView(R.id.minLight)
    EditText minLight;
    @BindView(R.id.maxLight)
    EditText maxLight;
    @BindView(R.id.minSoilTemperature)
    EditText minSoilTemperature;
    @BindView(R.id.maxSoilTemperature)
    EditText maxSoilTemperature;
    @BindView(R.id.minSoilHumidity)
    EditText minSoilHumidity;
    @BindView(R.id.maxSoilHumidity)
    EditText maxSoilHumidity;
    @BindView(R.id.minAirTemperature)
    EditText minAirTemperature;
    @BindView(R.id.maxAirTemperature)
    EditText maxAirTemperature;
    @BindView(R.id.minAirHumidity)
    EditText minAirHumidity;
    @BindView(R.id.maxAirHumidity)
    EditText maxAirHumidity;

    public ShouDongKongZhi() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shou_dong_kong_zhi, container, false);
        ButterKnife.bind(this, view);

        getFocus(view);
        getRange();
        return view;
    }

    /**
     * 获取最小最大值
     */
    private void getRange() {
        Call<Config> getRange = HttpUtils.request().getConfig();
        getRange.enqueue(new Callback<Config>() {

            @Override
            public void onResponse(Call<Config> call, Response<Config> response) {
                Log.e(TAG, "getRange请求成功");
                Config getConfig = response.body();
                minCo2.setText(getConfig.getMinCo2() + "");
                maxCo2.setText(getConfig.getMaxCo2() + "");
                minLight.setText(getConfig.getMinLight() + "");
                maxLight.setText(getConfig.getMaxLight() + "");
                minSoilTemperature.setText(getConfig.getMinSoilTemperature() + "");
                maxSoilTemperature.setText(getConfig.getMaxSoilTemperature() + "");
                minSoilHumidity.setText(getConfig.getMinSoilHumidity() + "");
                maxSoilHumidity.setText(getConfig.getMaxSoilHumidity() + "");
                minAirTemperature.setText(getConfig.getMinAirTemperature() + "");
                maxAirTemperature.setText(getConfig.getMaxAirTemperature() + "");
                minAirHumidity.setText(getConfig.getMinAirHumidity() + "");
                maxAirHumidity.setText(getConfig.getMaxAirHumidity() + "");
            }

            @Override
            public void onFailure(Call<Config> call, Throwable throwable) {
                Log.e(TAG, "getRange请求失败");
                Log.e(TAG, throwable.getMessage());
            }
        });
    }

    /**
     * 设定最小最大值
     */
    private void setRange() {
        int myMinCo2 = Integer.parseInt(minCo2.getText().toString());
        int myMaxCo2 = Integer.parseInt(maxCo2.getText().toString());
        int myMinLight = Integer.parseInt(minLight.getText().toString());
        int myMaxLight = Integer.parseInt(maxLight.getText().toString());
        int myMinSoilTemperature = Integer.parseInt(minSoilTemperature.getText().toString());
        int myMaxSoilTemperature = Integer.parseInt(maxSoilTemperature.getText().toString());
        int myMinSoilHumidity = Integer.parseInt(minSoilHumidity.getText().toString());
        int myMaxSoilHumidity = Integer.parseInt(maxSoilHumidity.getText().toString());
        int myMinAirTemperature = Integer.parseInt(minAirTemperature.getText().toString());
        int myMaxAirTemperature = Integer.parseInt(maxAirTemperature.getText().toString());
        int myMinAirHumidity = Integer.parseInt(minAirHumidity.getText().toString());
        int myMaxAirHumidity = Integer.parseInt(maxAirHumidity.getText().toString());

        Config config = new Config(myMinCo2, myMaxCo2, myMinLight, myMaxLight, myMinSoilTemperature,
                myMaxSoilTemperature, myMinSoilHumidity, myMaxSoilHumidity, myMinAirTemperature,
                myMaxAirTemperature, myMinAirHumidity, myMaxAirHumidity);
        Call<Config> setRange = HttpUtils.request().setConfig(config);
        setRange.enqueue(new Callback<Config>() {
            @Override
            public void onResponse(Call<Config> call, Response<Config> response) {
                Toast.makeText(getActivity(), "设置成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Config> call, Throwable t) {
                Toast.makeText(getActivity(), "设置失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(value = {R.id.shoudong_fanhui, R.id.submitBtn})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shoudong_fanhui:
                back();
                break;
            case R.id.submitBtn:
                setRange();
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
