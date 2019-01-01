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
import static org.junit.Assert.assertNotNull;

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
        assertEquals("http://www.garmin.com", gpx.getMetadata().getLink().getHref());
        assertEquals("Garmin International", gpx.getMetadata().getLink().getText());
        assertNotNull(gpx.getMetadata().getBounds());

        assertEquals(1, gpx.getTracks().size());
        assertEquals(1, gpx.getTracks().get(0).getTrackSegments().size());
        assertEquals(10, gpx.getTracks().get(0).getTrackSegments().get(0).getTrackPoints().size());
        assertEquals(3, gpx.getWayPoints().size());
        assertEquals(1, gpx.getRoutes().size());
        assertEquals(7, gpx.getRoutes().get(0).getRoutePoints().size());
        assertEquals(" A92", gpx.getWayPoints().get(0).getDesc());
        assertEquals("Erding Ab", gpx.getWayPoints().get(2).getDesc());
        assertEquals("user", gpx.getWayPoints().get(0).getType());
    }

    @Test(expected = XmlPullParserException.class)
    public void testGarminBaseCampExportTruncated() throws IOException, XmlPullParserException {
        InputStream input = InstrumentationRegistry.getContext().getAssets().open("garminBaseCampExport-truncated.gpx");
        new GPXParser().parse(input);
    }

    @Test(expected = XmlPullParserException.class)
    public void testGarminBaseCampExportNoClosingTag() throws IOException, XmlPullParserException {
        InputStream input = InstrumentationRegistry.getContext().getAssets().open("garminBaseCampExport-noclosingtag.gpx");
        Gpx gpx = new GPXParser().parse(input);
        assertEquals(1, gpx.getTracks().size());
    }


}
