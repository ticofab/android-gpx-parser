package io.ticofab.androidgpxparser.parser;

import android.content.res.AssetManager;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import io.ticofab.androidgpxparser.parser.domain.Author;
import io.ticofab.androidgpxparser.parser.domain.Copyright;
import io.ticofab.androidgpxparser.parser.domain.Email;
import io.ticofab.androidgpxparser.parser.domain.Gpx;
import io.ticofab.androidgpxparser.parser.domain.Link;
import io.ticofab.androidgpxparser.parser.domain.Metadata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class GPXParserTest {

    @Test
    public void testShoresOfDerwentwater() throws IOException, XmlPullParserException {
        InputStream input = getAssets().open("shores-of-derwentwater.xml");
        Gpx gpx = new GPXParser().parse(input);
        assertNotNull(gpx); // testing that there is no crash, really
    }

    @Test
    public void testWadlbeisserExport() throws IOException, XmlPullParserException {
        InputStream input = getAssets().open("wadlbeisserExport.gpx");
        Gpx gpx = new GPXParser().parse(input);
        assertEquals(0, gpx.getTracks().size());
        assertEquals(2, gpx.getWayPoints().size());
        assertEquals(1, gpx.getRoutes().size());
        assertEquals(7847, gpx.getRoutes().get(0).getRoutePoints().size());
    }

    @Test
    public void testGarminBaseCampExport() throws IOException, XmlPullParserException {
        InputStream input = getAssets().open("garminBaseCampExport.gpx");
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
        InputStream input = getAssets().open("garminBaseCampExport-truncated.gpx");
        new GPXParser().parse(input);
    }

    @Test(expected = XmlPullParserException.class)
    public void testGarminBaseCampExportNoClosingTag() throws IOException, XmlPullParserException {
        InputStream input = getAssets().open("garminBaseCampExport-noclosingtag.gpx");
        Gpx gpx = new GPXParser().parse(input);
        assertEquals(1, gpx.getTracks().size());
    }

    @Test
    public void testFullMetadataParsing() throws IOException, XmlPullParserException {
        InputStream input = getAssets().open("metadata-full.gpx");
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
        InputStream input = getAssets().open("metadata-minimal.gpx");
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

    public AssetManager getAssets() {
        return InstrumentationRegistry.getInstrumentation().getContext().getAssets();
    }
}
