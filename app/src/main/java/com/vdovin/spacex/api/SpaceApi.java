package com.vdovin.spacex.api;

import com.vdovin.spacex.model.Space;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface SpaceApi {
    @GET("v2/launches")
    Observable<List<Space>> getAllPastLaunches();
}
