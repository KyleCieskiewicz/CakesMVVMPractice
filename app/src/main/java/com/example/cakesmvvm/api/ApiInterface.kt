package com.example.cakesmvvm.api

import com.example.cakesmvvm.model.Cake_model
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    //    @GET("movie/top_rated/{user}")
    //    Observable<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey, (@Path("user") String user);
    //
    //    @GET("movie/{id}")
    //    Observable<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
    @GET("198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json")
    suspend fun getCakeslist(): Response<List<Cake_model>>
}