package io.ticofab.androidgpxparser.parser;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import net.danlew.android.joda.JodaTimeAndroid;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import io.ticofab.androidgpxparser.parser.domain.Gpx;

import static org.junit.Assert.assertEquals;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class GPXParserTest {

    @Before
    public void setUp() {
        JodaTimeAndroid.init(InstrumentationRegistry.getTargetContext());
    }

    @Test
    public void testWadlbeisserExport() throws IOException, XmlPullParserException {
        InputStream input = InstrumentationRegistry.getContext().getAssets().open("wadlbeisserExport.gpx");
        Gpx gpx = new GPXParser().parse(input);
        assertEquals(0, gpx.getTracks().size());
        assertEquals(2, gpx.getWayPoints().size());
        assertEquals(1, gpx.getRoutes().size());
        assertEquals(7847, gpx.getRoutes().get(0).getRoutePoints().size());
    }

    @Test
    public void testGarminBaseCampExport() throws IOException, XmlPullParserException {
        InputStream input = InstrumentationRegistry.getContext().getAssets().open("garminBaseCampExport.gpx");
        Gpx gpx = new GPXParser().parse(input);
        assertEquals(1, gpx.getTracks().size());
        assertEquals(1, gpx.getTracks().get(0).getTrackSegments().size());
        assertEquals(10, gpx.getTracks().get(0).getTrackSegments().get(0).getTrackPoints().size());
        assertEquals(3, gpx.getWayPoints().size());
        assertEquals(1, gpx.getRoutes().size());
        assertEquals(7, gpx.getRoutes().get(0).getRoutePoints().size());
    }

}
