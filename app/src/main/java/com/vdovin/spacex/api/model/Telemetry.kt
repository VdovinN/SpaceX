package com.vdovin.spacex.api.model

import com.google.gson.annotations.SerializedName

data class Telemetry(@SerializedName("flight_club") val flightClub: String?)