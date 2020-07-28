package com.vdovin.spacex.api.model

import com.google.gson.annotations.SerializedName

data class Space(
        @SerializedName("flight_number") val flightNumber: Long,
        @SerializedName("mission_name") val missionName: String?,
        @SerializedName("launch_year") val launchYear: String?,
        @SerializedName("launch_date_unix") val launchDateUnix: Long?,
        @SerializedName("launch_date_utc") val launchDateUtc: String?,
        @SerializedName("launch_date_local") val launchDateLocal: String?,
        @SerializedName("rocket") val rocket: Rocket?,
        @SerializedName("telemetry") val telemetry: Telemetry?,
        @SerializedName("reuse") val reuse: Reuse?,
        @SerializedName("launch_site") val launchSite: LaunchSite?,
        @SerializedName("launch_success") val launchSuccess: Boolean?,
        @SerializedName("links") val links: Links?,
        @SerializedName("details") val details: String?
)