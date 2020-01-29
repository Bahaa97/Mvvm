package com.bahaa.mvvm.util;

/**
 * Created by Elmohandes on 20/09/2018.
 */

public interface AppTools {

    int HOME_PAGE_ID = 8;
    int QUESTIONS_PAGE_ID = 28;
    int TRY_AGAIN = 501;
    String BACK = "back";
    String OPEN_MOVIE = "open movie";


    interface Network {
        String TOKEN = "dac05f837ca6a3d87f9503197bdb51bf";
        String IMAGE_URL = "https://image.tmdb.org/t/p/w500/";
        String TOP_RATED = "top_rated";
        String API_KEY = "api_key";
        String POPULAR = "popular";
        String LATEST = "latest";
        String UP_COMING = "upcoming";
    }
}
