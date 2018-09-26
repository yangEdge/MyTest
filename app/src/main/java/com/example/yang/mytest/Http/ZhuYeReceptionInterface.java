package com.example.yang.mytest.Http;

import com.example.yang.mytest.Bean.ControlBean.Blower;
import com.example.yang.mytest.Bean.ControlBean.Buzzer;
import com.example.yang.mytest.Bean.ControlBean.Roadlamp;
import com.example.yang.mytest.Bean.ControlBean.WaterPump;
import com.example.yang.mytest.Bean.GetConfig;
import com.example.yang.mytest.Bean.ContorllerStatus;
import com.example.yang.mytest.Bean.ZhuYeReception;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ZhuYeReceptionInterface {
    @POST("getSensor")
    Call<ZhuYeReception> getSensor();

    @POST("getConfig")
    Call<GetConfig> getConfig();

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
