
package com.vdovin.spacex.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Payload {

    @SerializedName("payload_id")
    @Expose
    private String payloadId;
    @SerializedName("reused")
    @Expose
    private Boolean reused;
    @SerializedName("customers")
    @Expose
    private List<String> customers = null;
    @SerializedName("payload_type")
    @Expose
    private String payloadType;
    @SerializedName("payload_mass_kg")
    @Expose
    private Double payloadMassKg;
    @SerializedName("payload_mass_lbs")
    @Expose
    private Double payloadMassLbs;
    @SerializedName("orbit")
    @Expose
    private String orbit;
    @SerializedName("orbit_params")
    @Expose
    private OrbitParams orbitParams;

    public String getPayloadId() {
        return payloadId;
    }

    public void setPayloadId(String payloadId) {
        this.payloadId = payloadId;
    }

    public Boolean getReused() {
        return reused;
    }

    public void setReused(Boolean reused) {
        this.reused = reused;
    }

    public List<String> getCustomers() {
        return customers;
    }

    public void setCustomers(List<String> customers) {
        this.customers = customers;
    }

    public String getPayloadType() {
        return payloadType;
    }

    public void setPayloadType(String payloadType) {
        this.payloadType = payloadType;
    }

    public Double getPayloadMassKg() {
        return payloadMassKg;
    }

    public void setPayloadMassKg(Double payloadMassKg) {
        this.payloadMassKg = payloadMassKg;
    }

    public Double getPayloadMassLbs() {
        return payloadMassLbs;
    }

    public void setPayloadMassLbs(Double payloadMassLbs) {
        this.payloadMassLbs = payloadMassLbs;
    }

    public String getOrbit() {
        return orbit;
    }

    public void setOrbit(String orbit) {
        this.orbit = orbit;
    }

    public OrbitParams getOrbitParams() {
        return orbitParams;
    }

    public void setOrbitParams(OrbitParams orbitParams) {
        this.orbitParams = orbitParams;
    }

}
