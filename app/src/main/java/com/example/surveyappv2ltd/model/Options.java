package com.example.surveyappv2ltd.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Options {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("referTo")
    @Expose
    private Integer referTo;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getReferTo() {
        return referTo;
    }

    public void setReferTo(Integer referTo) {
        this.referTo = referTo;
    }

    @Override
    public String toString() {
        return "OptionsDetails{" +
                "value='" + value + '\'' +
                ", referTo=" + referTo +
                '}';
    }
}
