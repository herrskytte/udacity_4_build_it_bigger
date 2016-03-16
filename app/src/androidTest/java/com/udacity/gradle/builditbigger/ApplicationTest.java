package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.text.TextUtils;

import java.util.concurrent.CountDownLatch;

public class ApplicationTest extends ApplicationTestCase<Application> {
    String mJokeString = null;
    String mError = null;
    CountDownLatch signal = null;

    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }

    public void testGetJokeAsyncTask() throws InterruptedException {

        GetJokeAsyncTask task = new GetJokeAsyncTask(new GetJokeTaskListener() {
            @Override
            public void onSuccess(String joke) {
                mJokeString = joke;

                signal.countDown();
            }

            @Override
            public void onFailure(String exception) {
                mError = exception;
                signal.countDown();
            }
        });
        task.execute();
        signal.await();

        assertNull(mError);
        assertFalse(TextUtils.isEmpty(mJokeString));

    }
}