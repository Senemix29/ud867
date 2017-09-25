package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.natanximenes.Comedian;


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
        Toast.makeText(this, Comedian.tellJoke(), Toast.LENGTH_SHORT).show();
    }
}
