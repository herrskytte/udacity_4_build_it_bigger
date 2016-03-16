package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.JokeCreator;

import no.skytte.jokedisplayer.JokeDisplayerActivity;


public class MainActivity extends ActionBarActivity implements GetJokeTaskListener{

    JokeCreator mJokeCreator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJokeCreator = new JokeCreator();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){
        new GetJokeAsyncTask(this).execute();
    }

    @Override
    public void onSuccess(String joke) {
        Intent showJokeIntent = new Intent(this, JokeDisplayerActivity.class);
        showJokeIntent.putExtra(JokeDisplayerActivity.JOKE_EXTRA, joke);
        startActivity(showJokeIntent);
    }

    @Override
    public void onFailure(String exception) {
        Toast.makeText(this, R.string.error_download, Toast.LENGTH_LONG).show();
    }

}
