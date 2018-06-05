
package com.vdovin.spacex.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SecondStage {

    @SerializedName("payloads")
    @Expose
    private List<Payload> payloads = null;

    public List<Payload> getPayloads() {
        return payloads;
    }

    public void setPayloads(List<Payload> payloads) {
        this.payloads = payloads;
    }

}
