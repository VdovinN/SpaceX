package com.vdovin.spacex.database.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "space_x")
public class SpaceX implements Serializable {

    @PrimaryKey
    private Long flightNumber;

    private String missionName;

    private String details;

    private String launchDate;

    private String rocketName;

    private Double payloadMass;

    private String wikipediaLink;

    private String youtubeVideoId;

    @Ignore
    public SpaceX() {
    }

    public SpaceX(Long flightNumber, String missionName, String details, String launchDate, String rocketName, Double payloadMass, String wikipediaLink, String youtubeVideoId) {
        this.flightNumber = flightNumber;
        this.missionName = missionName;
        this.details = details;
        this.launchDate = launchDate;
        this.rocketName = rocketName;
        this.payloadMass = payloadMass;
        this.wikipediaLink = wikipediaLink;
        this.youtubeVideoId = youtubeVideoId;
    }

    public Long getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Long flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    public String getRocketName() {
        return rocketName;
    }

    public void setRocketName(String rocketName) {
        this.rocketName = rocketName;
    }

    public Double getPayloadMass() {
        return payloadMass;
    }

    public void setPayloadMass(Double payloadMass) {
        this.payloadMass = payloadMass;
    }

    public String getWikipediaLink() {
        return wikipediaLink;
    }

    public void setWikipediaLink(String wikipediaLink) {
        this.wikipediaLink = wikipediaLink;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public void setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
    }
}
