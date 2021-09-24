package com.su.primeavto.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModel {

    @SerializedName("OK")
    private int ok;
    @SerializedName("AUTO")
    private List<AutoModel> autos;

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }

    public List<AutoModel> getAutos() {
        return autos;
    }

    public void setAutos(List<AutoModel> autos) {
        this.autos = autos;
    }

    @NonNull
    @Override
    public String toString() {
        return "ResponseModel{" +
                "ok=" + ok +
                ", autos=" + autos +
                '}';
    }
}
