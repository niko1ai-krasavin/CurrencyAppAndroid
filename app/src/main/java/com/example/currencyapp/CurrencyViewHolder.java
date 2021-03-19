package com.example.currencyapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currencyapp.entities.Currency;

public class CurrencyViewHolder extends RecyclerView.ViewHolder {

    private TextView nominalTextView;
    private TextView nameTextView;
    private TextView valueTextView;

    public CurrencyViewHolder(@NonNull View itemView) {
        super(itemView);

        nominalTextView = itemView.findViewById(R.id.currencyNominal);
        nameTextView = itemView.findViewById(R.id.currencyName);
        valueTextView = itemView.findViewById(R.id.currencyValue);
    }

    public void bind(Currency currency) {
        nominalTextView.setText(String.valueOf(currency.getNominal()));
        nameTextView.setText(currency.getName());
        valueTextView.setText(String.valueOf(currency.getValue()));
    }
}
