package com.vdovin.spacex.api.model

import com.google.gson.annotations.SerializedName

class Reuse (
    @SerializedName("core") val core: Boolean?,
    @SerializedName("side_core1") val sideCore1: Boolean?,
    @SerializedName("side_core2") val sideCore2: Boolean?,
    @SerializedName("fairings") val fairings: Boolean?,
    @SerializedName("capsule") val capsule: Boolean?
)