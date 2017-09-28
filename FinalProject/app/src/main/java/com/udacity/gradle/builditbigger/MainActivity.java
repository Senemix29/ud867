package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.com.natanximenes.Comedian;
import br.com.natanximenes.jokeDisplayer.JokeDisplayerActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button jokeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jokeButton = findViewById(R.id.joke_button);
        jokeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent jokeIntent = new Intent(this, JokeDisplayerActivity.class);
        jokeIntent.putExtra(JokeDisplayerActivity.JOKE, Comedian.tellJoke());
        startActivity(jokeIntent);
    }
}
