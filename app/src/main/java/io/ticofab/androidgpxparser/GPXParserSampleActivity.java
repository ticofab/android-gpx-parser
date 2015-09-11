package io.ticofab.androidgpxparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import io.ticofab.androidgpxparser.parser.GPXParser;
import io.ticofab.androidgpxparser.parser.domain.Gpx;

public class GPXParserSampleActivity extends AppCompatActivity {

    static final String TAG = GPXParserSampleActivity.class.getSimpleName();

    GPXParser mParser = new GPXParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gpx parsedGpx = null;
        try {
            InputStream in = getAssets().open("test.gpx");
            parsedGpx = mParser.parse(in);
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }

        if (parsedGpx != null) {
            Log.d(TAG, parsedGpx.toString());
        } else {
            Log.e(TAG, "Error parsing gpx track!");
        }
    }
}
