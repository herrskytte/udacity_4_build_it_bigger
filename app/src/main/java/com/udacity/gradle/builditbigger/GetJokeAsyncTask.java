package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.example.fsk.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

public class GetJokeAsyncTask extends AsyncTask<Void, Void, String> {

    private GetJokeTaskListener mListener = null;
    private static MyApi myApiService = null;
    private boolean isError = false;

    public GetJokeAsyncTask(GetJokeTaskListener listener){
        mListener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            isError = true;
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(isError){
            mListener.onFailure(result);
        }
        else {
            mListener.onSuccess(result);
        }
    }
}