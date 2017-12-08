package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BaseMainFragmentActivityTest implements FragmentTestRule.Listener<MainActivityFragment> {
    protected static final String JOKE_RESULT = "a fool joke";

    @Rule
    public FragmentTestRule<MainActivityFragment> rule =
            new FragmentTestRule<>(MainActivityFragment.class);

    protected JokeEndpointAsyncTask jokeEndpointAsyncTaskMock;
    protected JokeService jokeServiceMock;
    protected MainActivityFragment fragment;

    @Before
    public void setUp() throws Exception {
        jokeEndpointAsyncTaskMock = new JokeEndpointAsyncTask(InstrumentationRegistry.getTargetContext()) {
            @Override
            protected String doInBackground(Void... params) {
                return JOKE_RESULT;
            }
        };

        jokeServiceMock = new JokeService() {
            @Override
            public JokeEndpointAsyncTask retrieveJokeTask(Context context) {
                return jokeEndpointAsyncTaskMock;
            }
        };

        rule.setListener(this);
        rule.launchActivity(new Intent());
    }

    @Override
    public void afterFragmentTransaction(MainActivityFragment fragment) {
        this.fragment = fragment;
    }

    protected void waitMilis(Long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            return;
        }
    }
}
