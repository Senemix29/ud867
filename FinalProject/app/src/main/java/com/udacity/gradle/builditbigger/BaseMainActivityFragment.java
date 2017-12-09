package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.natanximenes.jokeDisplayer.JokeDisplayerActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class BaseMainActivityFragment extends Fragment implements JokeEndpointAsyncTask.Listener {
    Button jokeButton;
    JokeEndpointAsyncTask jokeEndpointAsyncTask;
    JokeService jokeService;
    ProgressBar progressBar;
    TextView instructionsTextView;
    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);

        jokeService = new JokeService();
        jokeButton = root.findViewById(R.id.joke_button);
        progressBar = root.findViewById(R.id.progress_bar);
        instructionsTextView = root.findViewById(R.id.instructions_text_view);

        return root;
    }

    @Override
    public void onJokeRetrieved(String result) {
        hideProgress();
        Intent jokeIntent = new Intent(getContext(), JokeDisplayerActivity.class);
        jokeIntent.putExtra(JokeDisplayerActivity.JOKE, result);
        jokeIntent.addFlags(FLAG_ACTIVITY_SINGLE_TOP);
        getContext().startActivity(jokeIntent);
    }

    protected void retrieveJoke() {
        showProgress();
        jokeEndpointAsyncTask = jokeService.retrieveJokeTask();
        jokeEndpointAsyncTask.setListener(this);
        jokeEndpointAsyncTask.execute();
    }

    @Override
    public void onDestroy() {
        if (jokeEndpointAsyncTask != null) {
            jokeEndpointAsyncTask.setListener(null);
        }
        super.onDestroy();
    }

    protected void showProgress() {
        jokeButton.setVisibility(GONE);
        instructionsTextView.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
    }

    protected void hideProgress() {
        jokeButton.setVisibility(VISIBLE);
        instructionsTextView.setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
    }
}
