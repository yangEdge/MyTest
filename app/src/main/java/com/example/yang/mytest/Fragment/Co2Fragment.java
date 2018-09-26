package com.example.yang.mytest.Fragment;


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
import android.widget.Toast;

import com.example.yang.mytest.Bean.ControlBean.Blower;
import com.example.yang.mytest.Bean.ControlBean.Buzzer;
import com.example.yang.mytest.Bean.GetConfig;
import com.example.yang.mytest.Bean.ContorllerStatus;
import com.example.yang.mytest.Http.BaseHttp;
import com.example.yang.mytest.Bean.ZhuYeReception;
import com.example.yang.mytest.R;
import com.example.yang.mytest.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Co2Fragment extends Fragment{
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
        BaseHttp.getContorllerStatus(co2_fengshan,null,null,co2_baojing);
        return view;
    }

    private void getSensor() {
        Call<ZhuYeReception> call = BaseHttp.request().getSensor();
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

    private void getConfig() {
        Call<GetConfig> call = BaseHttp.request().getConfig();
        call.enqueue(new Callback<GetConfig>() {

            @Override
            public void onResponse(Call<GetConfig> call, Response<GetConfig> response) {
                GetConfig getConfig = response.body();
                co2_minCo2.setText(getConfig.getMinCo2() + "");
                co2_maxCo2.setText(getConfig.getMaxCo2() + "");
            }

            @Override
            public void onFailure(Call<GetConfig> call, Throwable throwable) {
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
                BaseHttp.controlBlower(co2_fengshan);
                break;
            case R.id.co2_baojing:
                BaseHttp.controlBuzzer(co2_baojing);
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