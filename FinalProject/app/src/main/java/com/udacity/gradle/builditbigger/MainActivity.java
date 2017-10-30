package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button jokeButton;
    JokeEndpointAsyncTask jokeEndpointAsyncTask;
    JokeService jokeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jokeService = new JokeService();

        jokeButton = findViewById(R.id.joke_button);
        jokeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        jokeEndpointAsyncTask = jokeService.retrieveJokeTask(this);
        jokeEndpointAsyncTask.execute();
    }
}
