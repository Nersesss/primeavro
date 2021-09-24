package com.su.primeavto.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class NameValuesModel implements Serializable {

    @SerializedName("NAME")
    private String name;
    @SerializedName("VALUE")
    private List<String> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "NameValuesModel{" +
                "name='" + name + '\'' +
                ", values=" + values +
                '}';
    }
}
