package com.bahaa.mvvm.network;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkInterceptor implements Interceptor {

    /*
     * Step 1: We pass the context instance here,
     * since we need to get the ConnectivityStatus
     * to check if there is internet.
     * */
    private Context context;
    private NetworkEvent networkEvent;

    public NetworkInterceptor(Context context) {
        this.context = context;
        this.networkEvent = NetworkEvent.getInstance();
    }

    @Override
    public Response intercept(Chain chain) {
        Request request = chain.request();

        /*
         * Step 2: We check if there is internet
         * available in the device. If not, pass
         * the networkState as NO_INTERNET.
         * */

        if (!ConnectivityStatus.isConnected(context)) {
            networkEvent.publish(NetworkState.NO_INTERNET);
            Log.e("SSS", "NO FUCKING NET");
        } else {
            try {
                Response response = chain.proceed(request);
                Log.e("SSS", "code : " + response.code() + " " + request.url());
                switch (response.code()) {
                    case 401:
                        networkEvent.publish(NetworkState.UNAUTHORISED);
                        break;
                    case 500:
                        networkEvent.publish(NetworkState.SERVER_ERROR);
                        break;
                    case 404:
                        networkEvent.publish(NetworkState.API_NOT_FOUND);
                        break;
                    case 405:
                        networkEvent.publish(NetworkState.NOT_ALLOWED_METHOD);
                        break;
                    case 503:
                        networkEvent.publish(NetworkState.MAINTENANCE);
                        break;
                    case 400:
                    case 403:
                        String errorMessage403 = "";
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject (response.body().string());
                            errorMessage403 = jsonObject.optJSONArray("error").getString(0);
                        } catch (JSONException e) {
                            Log.e("SSS", e.getMessage());
                        }
                        if (jsonObject != null) {
                            handle400(errorMessage403, jsonObject);
                        }
                        break;
                }
                return response;

            } catch (IOException e) {
                Log.e("SSS", e.getMessage());
                networkEvent.publish(NetworkState.NO_RESPONSE);
            }
        }
        return null;
    }

    private void handle400(String message, JSONObject jsonObject){
        switch (message){
            case "User is logged.":
                networkEvent.publish(NetworkState.ALREADY_LOGIN);
                break;
            case "User is not logged.":
            case "User is not logged in":
                networkEvent.publish(NetworkState.UNAUTHORISED);
                break;
            default:
//                AppUtils.saveInSharedPreference(MyApplication.getContext(), "error", jsonObject.toString());
                networkEvent.publish(NetworkState.BAD_REQUEST);
        }
    }
}
