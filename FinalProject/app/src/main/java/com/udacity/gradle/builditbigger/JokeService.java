package com.udacity.gradle.builditbigger;

import android.content.Context;

public class JokeService {

    public JokeEndpointAsyncTask retrieveJokeTask(Context context) {
        return new JokeEndpointAsyncTask(context);
    }
}
