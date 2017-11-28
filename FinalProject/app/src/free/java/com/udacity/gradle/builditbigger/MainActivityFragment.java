package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivityFragment extends Fragment implements View.OnClickListener {
    InterstitialAd interstitialAd;
    Button jokeButton;
    JokeEndpointAsyncTask jokeEndpointAsyncTask;
    JokeService jokeService;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        jokeService = new JokeService();

        jokeButton = root.findViewById(R.id.joke_button);
        jokeButton.setOnClickListener(this);

        loadBannerAd(root);
        return root;
    }

    @Override
    public void onClick(View view) {
        loadInterstitialAd();
    }

    private void retrieveJoke() {
        jokeEndpointAsyncTask = jokeService.retrieveJokeTask(getContext());
        jokeEndpointAsyncTask.execute();
    }

    private void loadInterstitialAd() {
        interstitialAd = new InterstitialAd(getContext());
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                interstitialAd.show();
            }

            @Override
            public void onAdClosed() {
                retrieveJoke();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                retrieveJoke();
            }
        });
    }

    private void loadBannerAd(View view) {
        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }
}
