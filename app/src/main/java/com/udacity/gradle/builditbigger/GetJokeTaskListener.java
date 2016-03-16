package com.udacity.gradle.builditbigger;

public interface GetJokeTaskListener{
    void onSuccess(String joke);
    void onFailure(String exception);
}