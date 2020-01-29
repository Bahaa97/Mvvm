package com.bahaa.mvvm.data.network;

public class API {
    private static API instance;
    private API() {
    }
    public static API getInstance(){
        if (instance != null){
            return instance;
        }else {
            return new API();
        }
    }


//    @SuppressLint("CheckResult")
//    public MutableLiveData<MoviesResponse> getTopRated(){
//        RetrofitClient.webService().getTopRated(AppTools.Network.TOKEN)
//                .repeatWhen(completed -> completed.delay(59, TimeUnit.SECONDS))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(result -> {
//
//                }, throwable -> {
//
//                });
//    }

}
