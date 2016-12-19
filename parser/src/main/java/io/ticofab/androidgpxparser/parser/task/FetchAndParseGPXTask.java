package io.ticofab.androidgpxparser.parser.task;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.ticofab.androidgpxparser.parser.GPXParser;
import io.ticofab.androidgpxparser.parser.domain.Gpx;

public class FetchAndParseGPXTask extends AsyncTask<Void, Void, Gpx> {

    final String mGpxUrl;
    final GpxFetchedAndParsed mListener;
    final GPXParser mParser = new GPXParser();

    public FetchAndParseGPXTask(String gpxUrl, GpxFetchedAndParsed listener) {
        mGpxUrl = gpxUrl;
        mListener = listener;
    }

    @Override
    protected Gpx doInBackground(Void... unused) {
        Gpx parsedGpx = null;
        try {
            URL url = new URL(mGpxUrl);
            HttpURLConnection client = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(client.getInputStream());
            parsedGpx = mParser.parse(in);
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        return parsedGpx;
    }

    @Override
    protected void onPostExecute(Gpx gpx) {
        mListener.onGpxFetchedAndParsed(gpx);
    }
}
