package com.example.currencyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.currencyapp.entities.Currency;
import com.example.currencyapp.entities.DailyCurrentData;
import com.example.currencyapp.services.CbrCurrencyService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    public static final String ACTION = "com.example.currencyapp.MainActivity";

    public static DailyCurrentData dailyCurrentData;
    public static ArrayList<Currency> currencies;

    private FloatingActionButton updateCurrenciesFAB;
    private RecyclerView recyclerView;
    private CurrencyRecyclerAdapter currencyRecyclerAdapter;

    private BroadcastReceiver broadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updateCurrenciesFAB = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCurrencies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if(intent.getAction().equals(ACTION)) {
                    currencies = getCurrencies();

                    currencyRecyclerAdapter = new CurrencyRecyclerAdapter();
                    recyclerView.setAdapter(currencyRecyclerAdapter);
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter(ACTION);
        registerReceiver(broadcastReceiver, intentFilter);


        updateCurrenciesFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CbrCurrencyService.class);
                CbrCurrencyService.startEnqueueWork(MainActivity.this, intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    private ArrayList<Currency> getCurrencies() {
        HashMap<String, Currency> map = (HashMap<String, Currency>) dailyCurrentData.getValute();
        Collection<Currency> collection = map.values();
        ArrayList<Currency> arrayList = new ArrayList<>(collection);
        return arrayList;
    }
}