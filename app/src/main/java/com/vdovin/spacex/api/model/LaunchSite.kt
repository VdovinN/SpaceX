package com.vdovin.spacex.api.model

import com.google.gson.annotations.SerializedName

class LaunchSite (
    @SerializedName("site_id") val siteId: String,
    @SerializedName("site_name") val siteName: String,
    @SerializedName("site_name_long") val siteNameLong: String
)