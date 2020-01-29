package com.bahaa.mvvm.data.network.interfaces;


import com.bahaa.mvvm.models.movieDetails.MovieDetails;
import com.bahaa.mvvm.models.movieDetails.MoviesResponse;
import com.bahaa.mvvm.util.AppTools;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Api {

    @GET
    Observable<MoviesResponse> getMovies(@Url String url,@Query(AppTools.Network.API_KEY)String token);

    @GET("{id}")
    Observable<MovieDetails> getMovieDetails(@Path("id") String id, @Query(AppTools.Network.API_KEY)String token);


    @GET("{id}/similar")
    Observable<MoviesResponse> getRelated(@Path("id") String id, @Query(AppTools.Network.API_KEY)String token);


}
