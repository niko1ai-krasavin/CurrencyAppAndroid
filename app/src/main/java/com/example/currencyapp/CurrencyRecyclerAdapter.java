package com.example.currencyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.currencyapp.entities.Currency;


public class CurrencyRecyclerAdapter extends RecyclerView.Adapter<CurrencyViewHolder> {

    private List<Currency> currencyList;

    public CurrencyRecyclerAdapter() {
        currencyList = MainActivity.currencies;
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.currency_item_view, parent, false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        Currency currency = currencyList.get(position);
        holder.bind(currency);
    }

    @Override
    public int getItemCount() {
        if(currencyList != null) return currencyList.size();
        return 0;
    }

    public void setCurrency(List<Currency> currency) {
        if(!this.currencyList.isEmpty()) this.currencyList.clear();
        this.currencyList.addAll(currency);
        notifyDataSetChanged();
    }
}
