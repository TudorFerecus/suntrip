package com.smartherd.suntrip;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("maps/api/directions/json")
    Single<DirectionsResponse> getDirection(@Query("origin")String destination,
                                            @Query("destination")String origin,
                                            @Query("key") String key);
}
