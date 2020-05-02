package com.vdovin.spacex.api.model

import com.google.gson.annotations.SerializedName

class Links (
    @SerializedName("mission_patch") val missionPatch: String?,
    @SerializedName("mission_patch_small") val missionPatchSmall: String?,
    @SerializedName("article_link") val articleLink: String?,
    @SerializedName("wikipedia") val wikipedia: String?,
    @SerializedName("video_link") val videoLink: String?
)