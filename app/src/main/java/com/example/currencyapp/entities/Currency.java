package com.example.currencyapp.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Currency implements Serializable {
    @Expose
    @SerializedName("ID")
    private String id;
    @Expose
    @SerializedName("NumCode")
    private String numCode;
    @Expose
    @SerializedName("CharCode")
    private String charCode;
    @Expose
    @SerializedName("Nominal")
    private int nominal;
    @Expose
    @SerializedName("Name")
    private String name;
    @Expose
    @SerializedName("Value")
    private double value;
    @Expose
    @SerializedName("Previous")
    private double previous;

    public Currency() {
    }

    public Currency(String id, String numCode, String charCode, int nominal, String name, double value, double previous) {
        this.id = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
        this.previous = previous;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getPrevious() {
        return previous;
    }

    public void setPrevious(double previous) {
        this.previous = previous;
    }
}
