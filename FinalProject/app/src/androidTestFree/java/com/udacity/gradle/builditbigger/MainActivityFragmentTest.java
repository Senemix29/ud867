package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.app.Instrumentation.ActivityResult;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;

import org.junit.Before;
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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@RunWith(JUnit4.class)
public class MainActivityFragmentTest extends BaseMainFragmentActivityTest {

    @Test
    public void testIfTheJokeDisplayerWasCalled() {
        ActivityResult result = new ActivityResult(Activity.RESULT_OK, new Intent());
        intending(hasComponent(JokeDisplayerActivity.class.getName())).respondWith(result);

        onView(withId(R.id.joke_button)).perform(click());

        fragment.jokeService = jokeServiceMock;

        waitMilis(2000L);

        closeInterstitialAd();

        intended(allOf(hasExtra(JokeDisplayerActivity.JOKE, JOKE_RESULT),
                hasComponent(JokeDisplayerActivity.class.getName())));
    }

    public void closeInterstitialAd() {
        ViewInteraction closeInterstitialButton = onView(
                allOf(withContentDescription("Interstitial close button"), isDisplayed()));
        closeInterstitialButton.perform(click());
    }

}