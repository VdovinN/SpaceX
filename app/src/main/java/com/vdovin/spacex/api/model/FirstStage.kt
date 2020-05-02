package com.vdovin.spacex.api.model

import com.google.gson.annotations.SerializedName

class FirstStage(@SerializedName("cores") val cores: List<Core>?)