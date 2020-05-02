package com.vdovin.spacex.api

import com.vdovin.spacex.api.model.Space
import retrofit2.http.GET

interface SpaceApi {
    @GET("v2/launches")
    suspend fun allPastLaunches(): List<Space>
}