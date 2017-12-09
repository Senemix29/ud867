package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.app.Instrumentation.ActivityResult;
import android.content.Intent;

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
public class MainActivityFragmentTest extends BaseMainFragmentActivityTest {

    @Test
    public void testIfTheJokeDisplayerWasCalled() {
        ActivityResult result = new ActivityResult(Activity.RESULT_OK, new Intent());
        intending(hasComponent(JokeDisplayerActivity.class.getName())).respondWith(result);

        fragment.jokeService = jokeServiceMock;

        onView(withId(R.id.joke_button)).perform(click());

        intended(allOf(hasExtra(JokeDisplayerActivity.JOKE, JOKE_RESULT),
                hasComponent(JokeDisplayerActivity.class.getName())));
    }

}