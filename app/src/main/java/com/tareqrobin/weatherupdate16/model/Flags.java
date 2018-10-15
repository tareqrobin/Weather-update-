package com.tareqrobin.weatherupdate16.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Flags implements Serializable
{

    @SerializedName("sources")
    @Expose
    private List<String> sources = null;
    @SerializedName("units")
    @Expose
    private String units;
    private final static long serialVersionUID = 8365485267942313603L;

    public List<String> getSources() {
        return sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

}
