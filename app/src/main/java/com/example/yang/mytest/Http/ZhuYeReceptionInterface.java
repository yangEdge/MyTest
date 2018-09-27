package com.example.yang.mytest.Http;

import com.example.yang.mytest.Bean.ControlBean.Blower;
import com.example.yang.mytest.Bean.ControlBean.Buzzer;
import com.example.yang.mytest.Bean.ControlBean.Roadlamp;
import com.example.yang.mytest.Bean.ControlBean.WaterPump;
import com.example.yang.mytest.Bean.Config;
import com.example.yang.mytest.Bean.ContorllerStatus;
import com.example.yang.mytest.Bean.ZhuYeReception;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ZhuYeReceptionInterface {
    @POST("getSensor")
    Call<ZhuYeReception> getSensor();

    @POST("getConfig")
    Call<Config> getConfig();

    @POST("setConfig")
    Call<Config> setConfig(@Body Config json);

    @POST("getContorllerStatus")
    Call<ContorllerStatus> getContorllerStatus();

    @POST("control")
    Call<ContorllerStatus> controlBlower(@Body Blower json);

    @POST("control")
    Call<ContorllerStatus> controlBuzzer(@Body Buzzer json);

    @POST("control")
    Call<ContorllerStatus> controlWaterPump(@Body WaterPump json);

    @POST("control")
    Call<ContorllerStatus> controlRoadlamp(@Body Roadlamp json);
}
