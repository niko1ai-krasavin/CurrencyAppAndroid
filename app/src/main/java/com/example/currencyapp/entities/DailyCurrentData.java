package com.example.currencyapp.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class DailyCurrentData {

    @Expose
    @SerializedName("Date")
    private String date;
    @Expose
    @SerializedName("PreviousDate")
    private String previousDate;
    @Expose
    @SerializedName("PreviousURL")
    private String previousURL;
    @Expose
    @SerializedName("Timestamp")
    private String timestamp;
    @Expose
    @SerializedName("Valute")
    private HashMap<String, Currency> valute;


    public DailyCurrentData() {
    }

    public DailyCurrentData(String date, String previousDate, String previousURL, String timestamp, HashMap<String, Currency> valute) {
        this.date = date;
        this.previousDate = previousDate;
        this.previousURL = previousURL;
        this.timestamp = timestamp;
        this.valute = valute;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPreviousDate() {
        return previousDate;
    }

    public void setPreviousDate(String previousDate) {
        this.previousDate = previousDate;
    }

    public String getPreviousURL() {
        return previousURL;
    }

    public void setPreviousURL(String previousURL) {
        this.previousURL = previousURL;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Currency> getValute() {
        return valute;
    }

    public void setValute(HashMap<String, Currency> valute) {
        this.valute = valute;
    }
}
