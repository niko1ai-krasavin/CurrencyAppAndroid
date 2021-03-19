package com.example.currencyapp.services;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.example.currencyapp.MainActivity;
import com.example.currencyapp.entities.DailyCurrentData;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CbrCurrencyService extends JobIntentService {

    public static void startEnqueueWork(Context context, Intent intent) {
        CbrCurrencyService.enqueueWork(context, CbrCurrencyService.class, JOB_ID, intent);
    }

    private static final int JOB_ID = 1;
    private static final String CBR_URL = "https://www.cbr-xml-daily.ru/daily_json.js";

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Request request = new Request.Builder()
                .get()
                .url(CBR_URL)
                .addHeader("accept", "application/javascript")
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response)  {
                String json = getDataFromCBR(call, response);
                setDailyCurrentData(json);
                sendReport();
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
        });
    }

    private String getDataFromCBR(Call call, Response response) {
        try (InputStream inputStream = response.body().byteStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            StringBuilder stringBuilder = new StringBuilder("");
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
            return stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setDailyCurrentData(String json) {
        Gson gson = new Gson();
        MainActivity.dailyCurrentData = gson.fromJson(json, DailyCurrentData.class);
    }

    private void sendReport() {
        Intent intentReport = new Intent(MainActivity.ACTION);
        sendBroadcast(intentReport);
    }
}
