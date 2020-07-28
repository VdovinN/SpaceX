package com.vdovin.spacex.api.model

import com.google.gson.annotations.SerializedName

data class SecondStage(@SerializedName("payloads") var payloads: List<Payload>?)