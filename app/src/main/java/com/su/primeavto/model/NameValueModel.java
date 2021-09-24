package com.su.primeavto.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NameValueModel implements Serializable {

    @SerializedName("NAME")
    private String name;
    @SerializedName("VALUE")
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "NameValueModel{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
