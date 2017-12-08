package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.natanximenes.jokeDisplayer.JokeDisplayerActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivityFragment extends Fragment implements View.OnClickListener, JokeEndpointAsyncTask.Listener {
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
        jokeEndpointAsyncTask = jokeService.retrieveJokeTask();
        jokeEndpointAsyncTask.setListener(this);
        jokeEndpointAsyncTask.execute();
    }

    @Override
    public void onJokeRetrieved(String result) {
        Intent jokeIntent = new Intent(getContext(), JokeDisplayerActivity.class);
        jokeIntent.putExtra(JokeDisplayerActivity.JOKE, result);
        jokeIntent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(jokeIntent);
    }

    @Override
    public void onDestroy() {
        if (jokeEndpointAsyncTask != null) {
            jokeEndpointAsyncTask.setListener(null);
        }
        super.onDestroy();
    }
}
