package io.ticofab.androidgpxparser.parser;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import io.ticofab.androidgpxparser.parser.domain.Author;
import io.ticofab.androidgpxparser.parser.domain.Copyright;
import io.ticofab.androidgpxparser.parser.domain.Email;
import io.ticofab.androidgpxparser.parser.domain.Gpx;
import io.ticofab.androidgpxparser.parser.domain.Link;
import io.ticofab.androidgpxparser.parser.domain.Metadata;
import io.ticofab.androidgpxparser.parser.domain.Track;
import io.ticofab.androidgpxparser.parser.domain.TrackPoint;
import io.ticofab.androidgpxparser.parser.domain.TrackSegment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class GPXParserTest {

    @Before
    public void setUp() {
        JodaTimeAndroid.init(InstrumentationRegistry.getTargetContext());
    }

    @Test
    public void testShoresOfDerwentwater() throws IOException, XmlPullParserException {
        InputStream input = InstrumentationRegistry.getContext().getAssets().open("shores-of-derwentwater.xml");
        Gpx gpx = new GPXParser().parse(input);
        assertNotNull(gpx); // testing that there is no crash, really
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

    @Test
    public void testFullMetadataParsing() throws IOException, XmlPullParserException {
        InputStream input = InstrumentationRegistry.getContext().getAssets().open("metadata-full.gpx");
        Gpx gpx = new GPXParser().parse(input);

        final Metadata metadata = gpx.getMetadata();
        // Name
        assertEquals("metadata-full", metadata.getName());
        assertEquals("Full metadata test", metadata.getDesc());

        // Author
        final Author author = metadata.getAuthor();
        assertEquals("John Doe", author.getName());

        // Author email
        final Email email = author.getEmail();
        assertEquals("john.doe", email.getId());
        assertEquals("example.org", email.getDomain());

        // Author link
        final Link authorLink = author.getLink();
        assertEquals("www.example.org", authorLink.getHref());
        assertEquals("Example Org.", authorLink.getText());
        assertEquals("text/html", authorLink.getType());

        // Copyright
        final Copyright copyright = metadata.getCopyright();
        assertEquals("Jane Doe", copyright.getAuthor());
        assertEquals((Integer)2019, copyright.getYear());
        assertEquals("https://www.apache.org/licenses/LICENSE-2.0.txt", copyright.getLicense());

        // Link
        final Link link = metadata.getLink();
        assertEquals("www.example.org", link.getHref());
        assertNull(link.getText());
        assertNull(link.getType());

        // Time
        final DateTime expectedTime = DateTime.parse("2019-04-04T07:00:00+03:00");
        assertTrue(expectedTime.isEqual(metadata.getTime()));

        // Keywords
        assertEquals("metadata, test", metadata.getKeywords());
    }

    @Test
    public void testMinimalMetadataParsing() throws IOException, XmlPullParserException {
        InputStream input = InstrumentationRegistry.getContext().getAssets().open("metadata-minimal.gpx");
        Gpx gpx = new GPXParser().parse(input);

        final Metadata metadata = gpx.getMetadata();

        // Author
        final Author author = metadata.getAuthor();
        assertNull(author.getName());
        assertNull(author.getEmail());
        assertNull(author.getLink());

        // Copyright
        final Copyright copyright = metadata.getCopyright();
        assertEquals("Jane Doe", copyright.getAuthor());
        assertNull(copyright.getYear());
        assertNull(copyright.getLicense());
    }

    @Test
    public void testPoint() throws IOException, XmlPullParserException {
        InputStream input = InstrumentationRegistry.getContext().getAssets().open("testPoint.gpx");
        Gpx gpx = new GPXParser().parse(input);
        final Metadata metadata = gpx.getMetadata();
        assertNotNull(metadata.getLink());
        assertEquals(metadata.getLink().getText(),"Garmin International");
        assertEquals(metadata.getLink().getHref(),"http://www.garmin.com");

        List<Track> tracks = gpx.getTracks();
        assertEquals(1,tracks.size());
        List<TrackSegment> trackSegments = tracks.get(0).getTrackSegments();
        assertEquals(1,trackSegments.size());
        List<TrackPoint> trackPoints = trackSegments.get(0).getTrackPoints();
        assertEquals(3,trackPoints.size());
        for (TrackPoint points:trackPoints) {
            assertEquals(34,points.getSpeed(),0);
            assertEquals(89.33,points.getMagVar(),0);
            assertEquals(21.67,points.getHdop(),0);
            assertEquals(43.77,points.getVdop(),0);
            assertEquals(2342.676,points.getPdop(),0);
        }
    }
}
