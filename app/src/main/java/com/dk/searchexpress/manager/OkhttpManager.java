package com.dk.searchexpress.manager;

import android.os.Handler;
import android.os.Looper;

import com.dk.searchexpress.bean.ExpressQueryBean;
import com.dk.searchexpress.callback.IQueryExpressCallBack;
import com.dk.searchexpress.constants.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @ClassName: OkhttpManager
 * @Author: dongke
 * @Date: 2020/4/10 20:48
 * @Description:
 */
public class OkhttpManager {
    private static volatile OkhttpManager mOkHttpManager;
    private OkHttpClient mOkhttpClient;
    private static final int TIME_OUT = 5;
    private Handler mHandler;


    public static OkhttpManager getInstance() {
        if (mOkHttpManager == null) {
            synchronized (OkhttpManager.class) {
                if (mOkHttpManager == null) {
                    mOkHttpManager = new OkhttpManager();
                }
            }
        }
        return mOkHttpManager;
    }

    private OkhttpManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        mOkhttpClient = builder.build();
        mHandler = new Handler(Looper.getMainLooper());
    }

    public void execute(String url, Map<String, String> params, final IQueryExpressCallBack expressCallBack) {
        if (params == null) {
            return;
        }
        FormBody.Builder builder = new FormBody.Builder();
        Set<Map.Entry<String, String>> entries = params.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            builder.add(entry.getKey(), entry.getValue());
        }

        RequestBody requestBody = builder.build();

        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        mOkhttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (expressCallBack != null) {
                            expressCallBack.onQueryFail(Constant.ERROR_CODE.ERROR_OKHTTP, e.getMessage());
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseData = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (expressCallBack != null) {
                            if (response.isSuccessful()) {
                                ExpressQueryBean expressQueryBean1 = convertResult(responseData);
                                expressCallBack.onQuerySuccess(expressQueryBean1);
                            } else {
                                expressCallBack.onQueryFail(response.code(), response.message());
                            }
                        }
                    }
                });
            }
        });
    }

    private ExpressQueryBean convertResult(String jsonStr) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.fromJson(jsonStr, ExpressQueryBean.class);
    }
}
