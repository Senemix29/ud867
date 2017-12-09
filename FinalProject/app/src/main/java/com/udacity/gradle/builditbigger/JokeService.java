package com.udacity.gradle.builditbigger;

public class JokeService {

    public JokeEndpointAsyncTask retrieveJokeTask() {
        return new JokeEndpointAsyncTask();
    }
}
