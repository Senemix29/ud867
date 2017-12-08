package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivityFragment extends BaseMainActivityFragment implements View.OnClickListener, JokeEndpointAsyncTask.Listener {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        jokeButton.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {
        retrieveJoke();
    }
}
