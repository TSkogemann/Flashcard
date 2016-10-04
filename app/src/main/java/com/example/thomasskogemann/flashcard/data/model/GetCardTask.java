package com.example.thomasskogemann.flashcard.data.model;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Thomas Skogemann on 04-10-2016.
 */
public class GetCardTask extends AsyncTask<Object, Object, Object> {

    ApiListener listener;
    public GetCardTask(ApiListener apiListener) {
        this.listener=apiListener;
    }

    @Override
    protected Object doInBackground(Object... objects) {
        try {
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url("http://nodeserver-vilstrup.rhcloud.com/api/cards")
                    .build();

            okhttp3.Response response = ApiController.getInstance().getClient().newCall(request).execute();
            String jsonResponse = response.body().string();
            JSONArray json = new JSONArray(jsonResponse);

            if (response.isSuccessful()) {
                Log.i("GetCardTask",json.toString(2));
                //TempFlashcard tempCard =new TempFlashcard(json.getJSONObject(0));
                //TODO should be changed to handle several cards once the server is ready
                listener.onSucces(Flashcard.parseFrom(json.getJSONObject(0)));
            }
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }
        listener.onError();
        return null;
    }

    public interface ApiListener {
        abstract void onSucces(Flashcard tempFlashcard);
        abstract void onError();
    }

}
