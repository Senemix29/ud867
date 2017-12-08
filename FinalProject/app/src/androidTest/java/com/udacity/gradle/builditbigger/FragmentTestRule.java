package com.udacity.gradle.builditbigger;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import static junit.framework.Assert.fail;

public class FragmentTestRule<F extends Fragment> extends IntentsTestRule<TestActivity> {

    private final Class<F> fragmentClass;
    private F fragment;
    private Bundle bundle;
    private Listener<F> listener;

    public FragmentTestRule(final Class<F> fragmentClass) {
        super(TestActivity.class, true, false);
        this.fragmentClass = fragmentClass;
    }

    @Deprecated
    public FragmentTestRule(final Class<F> fragmentClass, Bundle args) {
        super(TestActivity.class, true, false);
        this.fragmentClass = fragmentClass;
        this.bundle = args;
    }

    /**
     * Inicia o teste espresso apartir de um fragment
     *
     * @param fragment Fragment a ser inflado
     */

    public void launchFragment(final F fragment) {
        this.fragment = fragment;
        launchActivity(null);
    }

    public void launchFragment() {
        launchFragment(null);
    }

    @Override
    protected void afterActivityLaunched() {
        super.afterActivityLaunched();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    if (fragment == null) {
                        fragment = fragmentClass.newInstance();
                        if (bundle != null) {
                            fragment.setArguments(bundle);
                        }
                    }
                    transaction.replace(R.id.fragment_container, fragment);

                    transaction.commit();
                } catch (InstantiationException e) {
                    assertFail(e);
                } catch (IllegalAccessException e) {
                    assertFail(e);
                }

                if (listener != null)
                    listener.afterFragmentTransaction(fragment);
            }
        });
    }

    @Deprecated
    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    /**
     * Utilizar {@link #launchFragment(F fragment)}
     *
     * @param startIntent
     * @return
     */
    @Override
    @Deprecated
    public TestActivity launchActivity(@Nullable Intent startIntent) {
        return super.launchActivity(startIntent);
    }

    private void assertFail(Exception e) {
        fail(String.format("%s: Não foi possível inserir %s na TestActivity: %s",
                getClass().getSimpleName(),
                fragmentClass.getSimpleName(),
                e.getMessage()));
    }

    public interface Listener<F> {

        void afterFragmentTransaction(F fragment);
    }

}
