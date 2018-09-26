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

import com.example.yang.mytest.Bean.ContorllerStatus;
import com.example.yang.mytest.Bean.ControlBean.Buzzer;
import com.example.yang.mytest.Bean.ControlBean.Roadlamp;
import com.example.yang.mytest.Bean.GetConfig;
import com.example.yang.mytest.Bean.ZhuYeReception;
import com.example.yang.mytest.Http.BaseHttp;
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
public class LightFragment extends Fragment{
    private static String TAG = "LightFragment";

    @BindView(R.id.light_details)
    TextView light_details;
    @BindView(R.id.light_minLight)
    TextView light_minLight;
    @BindView(R.id.light_maxLight)
    TextView light_maxLight;
    @BindView(R.id.light_guangzhao)
    ImageView light_guangzhao;
    @BindView(R.id.light_baojing)
    ImageView light_baojing;


    public LightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_light, container, false);
        ButterKnife.bind(this, view);

        getFocus(view);
        getSensor();
        getConfig();
        BaseHttp.getContorllerStatus(null,light_guangzhao,null,light_baojing);
        return view;
    }

    private void getSensor() {
        Call<ZhuYeReception> call = BaseHttp.request().getSensor();
        call.enqueue(new Callback<ZhuYeReception>() {

            @Override
            public void onResponse(Call<ZhuYeReception> call, Response<ZhuYeReception> response) {
                ZhuYeReception zhuYeReception = response.body();
                light_details.setText(zhuYeReception.getLight() + "");
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
                light_minLight.setText(getConfig.getMinLight() + "");
                light_maxLight.setText(getConfig.getMaxLight() + "");
            }

            @Override
            public void onFailure(Call<GetConfig> call, Throwable throwable) {
                Log.e(TAG, "请求失败");
                Log.e(TAG, throwable.getMessage());
            }
        });
    }

    @OnClick(value = {R.id.img_fanhui, R.id.light_guangzhao, R.id.light_baojing})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_fanhui:
                back();
                break;
            case R.id.light_guangzhao:
                BaseHttp.controlRoadlamp(light_guangzhao);
                break;
            case R.id.light_baojing:
                BaseHttp.controlBuzzer(light_baojing);
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
