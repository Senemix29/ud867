package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.app.Instrumentation.ActivityResult;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.natanximenes.jokeDisplayer.JokeDisplayerActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@RunWith(JUnit4.class)
public class MainActivityTest {
    private static final String JOKE_RESULT = "a fool joke";
    @Rule
    public IntentsTestRule<MainActivity> rule = new IntentsTestRule<>(MainActivity.class, false, false);
    private JokeEndpointAsyncTask jokeEndpointAsyncTaskMock;
    private JokeService jokeServiceMock;

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

        rule.launchActivity(new Intent());
    }

    @Test
    public void testIfTheJokeDisplayerWasCalled() {
        rule.getActivity().jokeService = jokeServiceMock;

        ActivityResult result = new ActivityResult(Activity.RESULT_OK, new Intent());
        intending(hasComponent(JokeDisplayerActivity.class.getName())).respondWith(result);

        onView(withId(R.id.joke_button)).perform(click());

        intended(allOf(hasExtra(JokeDisplayerActivity.JOKE, JOKE_RESULT),
                hasComponent(JokeDisplayerActivity.class.getName())));
    }


}