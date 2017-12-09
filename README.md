# Gradle for Android and Java Final Project

In this project, i created an app with multiple flavors that uses
multiple libraries and Google Cloud Endpoints. The finished app consists
of four modules. A Java library that provides jokes, a Google Cloud Endpoints
(GCE) project that serves those jokes, an Android Library containing an
activity for displaying jokes, and an Android app that fetches jokes from the
GCE module and passes them to the Android Library for display.

## Contains:

* Java library to provide joke

* Android Library to display a joke

* GCE Module that uses the java joke library to serve joke 

* Unit Tests for AsyncTask 

* Two Flavors: paid and free(with a banner ad)

* Instrumented test for both paid and free flavor

## Optional Tasks

* Interstitial Ad at free flavor

* Loading Indicator when fetching for jokes

### Configure Test Task
Configure a task to execute the tests isn't necessary because i  mock all network calls, so i can run unit tests or instrumented test without start GCE module.
