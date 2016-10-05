package com.example.thomasskogemann.flashcard.data.model;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Thomas Skogemann on 04-10-2016.
 */
public class GetCardTask extends AsyncTask<Object, Object, JSONArray> {

    ApiListener listener;
    public GetCardTask(ApiListener apiListener) {
        this.listener=apiListener;
    }


    @Override
    protected JSONArray doInBackground(Object... objects) {
        try {
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url("http://nodeserver-vilstrup.rhcloud.com/api/cards")
                    .build();

            okhttp3.Response response = ApiController.getInstance().getClient().newCall(request).execute();
            String jsonResponse = response.body().string();

            if (response.isSuccessful()) {
                JSONArray json = new JSONArray(jsonResponse);

                Log.i("GetCardTask",json.toString(2));
                //TempFlashcard tempCard =new TempFlashcard(json.getJSONObject(0));
                //TODO should be changed to handle several cards once the server is ready
                return json;
            }
            else
                return null;
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONArray o) {
        try {
            if(o == null)
                listener.onError();
            else
                listener.onSucces(Flashcard.parseFrom(o.getJSONObject(0)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public interface ApiListener {
        abstract void onSucces(Flashcard tempFlashcard);
        abstract void onError();
    }

}
