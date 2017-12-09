package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.example.natanximenes.myapplication.backend.jokeApi.JokeApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

public class JokeEndpointAsyncTask extends AsyncTask<Void, Void, String> {
    private static JokeApi jokeService = null;
    private Listener listener;

    public JokeEndpointAsyncTask() {
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (jokeService == null) {
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            jokeService = builder.build();
        }

        try {
            return jokeService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        listener.onJokeRetrieved(result);
    }

    public interface Listener {
        void onJokeRetrieved(String result);
    }

}
