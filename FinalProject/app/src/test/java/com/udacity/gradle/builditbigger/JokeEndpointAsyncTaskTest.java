package com.udacity.gradle.builditbigger;

import com.udacity.gradle.builditbigger.JokeEndpointAsyncTask.Listener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class JokeEndpointAsyncTaskTest {
    private final static String JOKE = "a foo joke";
    JokeEndpointAsyncTask jokeEndpointAsyncTask;
    Listener listener;

    @Before
    public void setUp() throws Exception {
        jokeEndpointAsyncTask = new JokeEndpointAsyncTask() {
            @Override
            protected String doInBackground(Void... params) {
                return JOKE;
            }
        };
        listener = mock(Listener.class);
        jokeEndpointAsyncTask.setListener(listener);
    }

    @Test
    public void testIfAJokeIsReturned() {
        jokeEndpointAsyncTask.execute();
        verify(listener).onJokeRetrieved(JOKE);
    }
}