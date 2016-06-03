package io.ticofab.androidgpxparser.parser.application;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

public class ParserApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // init joda date time
        JodaTimeAndroid.init(this);
    }
}
