package no.skytte.jokedisplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayerActivity extends AppCompatActivity {

    public static String JOKE_EXTRA = "no.skytte.jokedisplayer.jokeextra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_displayer);

        String joke = getIntent().getStringExtra(JOKE_EXTRA);
        TextView tv = (TextView) findViewById(R.id.joke_textview);
        tv.setText(joke);
    }
}
