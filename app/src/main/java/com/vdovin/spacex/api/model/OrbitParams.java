
package com.vdovin.spacex.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrbitParams {

    @SerializedName("reference_system")
    @Expose
    private String referenceSystem;
    @SerializedName("regime")
    @Expose
    private String regime;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("semi_major_axis_km")
    @Expose
    private Double semiMajorAxisKm;
    @SerializedName("eccentricity")
    @Expose
    private Double eccentricity;
    @SerializedName("periapsis_km")
    @Expose
    private Double periapsisKm;
    @SerializedName("apoapsis_km")
    @Expose
    private Double apoapsisKm;
    @SerializedName("inclination_deg")
    @Expose
    private Double inclinationDeg;
    @SerializedName("period_min")
    @Expose
    private Double periodMin;
    @SerializedName("lifespan_years")
    @Expose
    private Double lifespanYears;

    public String getReferenceSystem() {
        return referenceSystem;
    }

    public void setReferenceSystem(String referenceSystem) {
        this.referenceSystem = referenceSystem;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getSemiMajorAxisKm() {
        return semiMajorAxisKm;
    }

    public void setSemiMajorAxisKm(Double semiMajorAxisKm) {
        this.semiMajorAxisKm = semiMajorAxisKm;
    }

    public Double getEccentricity() {
        return eccentricity;
    }

    public void setEccentricity(Double eccentricity) {
        this.eccentricity = eccentricity;
    }

    public Double getPeriapsisKm() {
        return periapsisKm;
    }

    public void setPeriapsisKm(Double periapsisKm) {
        this.periapsisKm = periapsisKm;
    }

    public Double getApoapsisKm() {
        return apoapsisKm;
    }

    public void setApoapsisKm(Double apoapsisKm) {
        this.apoapsisKm = apoapsisKm;
    }

    public Double getInclinationDeg() {
        return inclinationDeg;
    }

    public void setInclinationDeg(Double inclinationDeg) {
        this.inclinationDeg = inclinationDeg;
    }

    public Double getPeriodMin() {
        return periodMin;
    }

    public void setPeriodMin(Double periodMin) {
        this.periodMin = periodMin;
    }

    public Double getLifespanYears() {
        return lifespanYears;
    }

    public void setLifespanYears(Double lifespanYears) {
        this.lifespanYears = lifespanYears;
    }

}
