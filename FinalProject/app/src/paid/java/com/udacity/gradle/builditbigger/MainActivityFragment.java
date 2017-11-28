package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivityFragment extends Fragment implements View.OnClickListener{
    Button jokeButton;
    JokeEndpointAsyncTask jokeEndpointAsyncTask;
    JokeService jokeService;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        jokeService = new JokeService();

        jokeButton = root.findViewById(R.id.joke_button);
        jokeButton.setOnClickListener(this);

        return root;
    }


    @Override
    public void onClick(View view) {
        retrieveJoke();
    }

    private void retrieveJoke() {
        jokeEndpointAsyncTask = jokeService.retrieveJokeTask(getContext());
        jokeEndpointAsyncTask.execute();
    }

}
