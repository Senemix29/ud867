package br.com.natanximenes.jokeDisplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeDisplayerActivity extends AppCompatActivity {
    public static final String JOKE = "JOKE";
    private String jokeToDisplay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_displayer);
        jokeToDisplay = getIntent().getExtras().getString(JOKE);
        TextView jokeText = findViewById(R.id.activity_joke_displayer_joke_text);

        if (jokeToDisplay != null) {
            jokeText.setText(jokeToDisplay);
        }
    }
}
