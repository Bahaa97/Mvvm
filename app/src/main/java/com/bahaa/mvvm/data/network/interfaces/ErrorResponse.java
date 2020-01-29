package com.bahaa.mvvm.data.network.interfaces;

import org.json.JSONObject;

public interface ErrorResponse {

    void onAlreadyLoggedIn();
    void onNoInternet();
    void onNotAuthorized();
    void onNotAllowedMethod();
    void onApiNotFound();
    void onBadRequest(JSONObject object);
    void onServerSideError();
    void onForbidden();
    void onMaintenance();
}
