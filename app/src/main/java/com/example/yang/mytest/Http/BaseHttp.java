package com.example.yang.mytest.Http;

import android.util.Log;
import android.widget.ImageView;

import com.example.yang.mytest.Bean.ContorllerStatus;
import com.example.yang.mytest.Bean.ControlBean.Blower;
import com.example.yang.mytest.Bean.ControlBean.Buzzer;
import com.example.yang.mytest.Bean.ControlBean.Roadlamp;
import com.example.yang.mytest.Bean.ControlBean.WaterPump;
import com.example.yang.mytest.Bean.GetConfig;
import com.example.yang.mytest.Bean.ZhuYeReception;
import com.example.yang.mytest.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseHttp {
    private static String TAG = "BaseHttp";
    public static String url = "http://192.168.1.106:8890/type/jason/action/";
    public static int flag_fengshan = 0;
    public static int flag_guangzhao = 0;
    public static int flag_shui = 0;
    public static int flag_baojing = 0;

    public static ZhuYeReceptionInterface request() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ZhuYeReceptionInterface zhuYeReceptionInterface = retrofit.create(ZhuYeReceptionInterface.class);

        return zhuYeReceptionInterface;
    }

    public static void getContorllerStatus(final ImageView fengshan_img, final ImageView guangzhao_img,
                                           final ImageView shui_img, final ImageView baojing_img) {
        Call<ContorllerStatus> call = BaseHttp.request().getContorllerStatus();
        call.enqueue(new Callback<ContorllerStatus>() {

            @Override
            public void onResponse(Call<ContorllerStatus> call, Response<ContorllerStatus> response) {
                Log.e(TAG, "getContorllerStatus请求成功");

                ContorllerStatus contorllerStatus = response.body();

                if (contorllerStatus.getBlower() == 1 && fengshan_img != null) {
                    flag_fengshan = 1;
                    fengshan_img.setImageResource(R.mipmap.dakaifengshan2);
                } else if (fengshan_img != null) {
                    flag_fengshan = 0;
                    fengshan_img.setImageResource(R.mipmap.dakaifengshan);
                }

                if (contorllerStatus.getRoadlamp() == 1 && guangzhao_img != null) {
                    flag_guangzhao = 1;
                    guangzhao_img.setImageResource(R.mipmap.dakaiguangzhao2);
                } else if (guangzhao_img != null) {
                    flag_guangzhao = 0;
                    guangzhao_img.setImageResource(R.mipmap.dakaiguangzhao);
                }

                if (contorllerStatus.getWaterPump() == 1 && shui_img != null) {
                    flag_shui = 1;
                    shui_img.setImageResource(R.mipmap.dakaishui2);
                } else if (shui_img != null) {
                    flag_shui = 0;
                    shui_img.setImageResource(R.mipmap.dakaishui);
                }

                if (contorllerStatus.getBuzzer() == 1 && baojing_img != null) {
                    flag_baojing = 1;
                    baojing_img.setImageResource(R.mipmap.dakaibaojing2);
                } else if (baojing_img != null) {
                    flag_baojing = 0;
                    baojing_img.setImageResource(R.mipmap.dakaibaojing);
                }
            }

            @Override
            public void onFailure(Call<ContorllerStatus> call, Throwable throwable) {
                Log.e(TAG, "请求失败");
                Log.e(TAG, throwable.getMessage());
            }
        });
    }

    public static void controlBlower(final ImageView fengshan_img) {
        Call<ContorllerStatus> call;
        if (flag_fengshan == 0) {
            call = BaseHttp.request().controlBlower(new Blower(1));
            flag_fengshan = 1;
        } else {
            call = BaseHttp.request().controlBlower(new Blower(0));
            flag_fengshan = 0;
        }
        call.enqueue(new Callback<ContorllerStatus>() {
            @Override
            public void onResponse(Call<ContorllerStatus> call, Response<ContorllerStatus> response) {
                Log.e(TAG, "controlBlower请求成功");
                getContorllerStatus(fengshan_img, null, null, null);
            }

            @Override
            public void onFailure(Call<ContorllerStatus> call, Throwable t) {
                Log.e(TAG, "controlBlower请求失败");
                Log.e(TAG, t.getMessage());
            }
        });

    }

    public static void controlRoadlamp(final ImageView guangzhao_img) {
        Call<ContorllerStatus> call;
        if (flag_guangzhao == 0) {
            call = BaseHttp.request().controlRoadlamp(new Roadlamp(1));
        } else
            call = BaseHttp.request().controlRoadlamp(new Roadlamp(0));
        call.enqueue(new Callback<ContorllerStatus>() {
            @Override
            public void onResponse(Call<ContorllerStatus> call, Response<ContorllerStatus> response) {
                Log.e(TAG, "controlRoadlamp请求成功");
                getContorllerStatus(null, guangzhao_img, null, null);
            }

            @Override
            public void onFailure(Call<ContorllerStatus> call, Throwable t) {
                Log.e(TAG, "controlRoadlamp请求失败");
                Log.e(TAG, t.getMessage());
            }
        });

    }

    public static void controlWaterPump(final ImageView shui_img) {
        Call<ContorllerStatus> call;
        if (flag_shui == 0) {
            call = BaseHttp.request().controlWaterPump(new WaterPump(1));
        } else
            call = BaseHttp.request().controlWaterPump(new WaterPump(0));
        call.enqueue(new Callback<ContorllerStatus>() {
            @Override
            public void onResponse(Call<ContorllerStatus> call, Response<ContorllerStatus> response) {
                Log.e(TAG, "controlWaterPump请求成功");
                getContorllerStatus(null, null, shui_img, null);
            }

            @Override
            public void onFailure(Call<ContorllerStatus> call, Throwable t) {
                Log.e(TAG, "controlBlower请求失败");
                Log.e(TAG, t.getMessage());
            }
        });

    }

    public static void controlBuzzer(final ImageView baojing_img) {
        Call<ContorllerStatus> call;
        if (flag_baojing == 0) {
            call = BaseHttp.request().controlBuzzer(new Buzzer(1));
        } else
            call = BaseHttp.request().controlBuzzer(new Buzzer(0));
        call.enqueue(new Callback<ContorllerStatus>() {
            @Override
            public void onResponse(Call<ContorllerStatus> call, Response<ContorllerStatus> response) {
                Log.e(TAG, "controlBuzzer请求成功");
                getContorllerStatus(null, null, null, baojing_img);
            }

            @Override
            public void onFailure(Call<ContorllerStatus> call, Throwable t) {
                Log.e(TAG, "controlBuzzer请求失败");
                Log.e(TAG, t.getMessage());
            }
        });

    }

}
