package io.ticofab.androidgpxparser.parser

import android.util.Xml

import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException

import java.io.IOException
import java.io.InputStream

import io.ticofab.androidgpxparser.parser.domain.Author
import io.ticofab.androidgpxparser.parser.domain.Bounds
import io.ticofab.androidgpxparser.parser.domain.Copyright
import io.ticofab.androidgpxparser.parser.domain.Email
import io.ticofab.androidgpxparser.parser.domain.Gpx
import io.ticofab.androidgpxparser.parser.domain.Link
import io.ticofab.androidgpxparser.parser.domain.Metadata
import io.ticofab.androidgpxparser.parser.domain.Point
import io.ticofab.androidgpxparser.parser.domain.Route
import io.ticofab.androidgpxparser.parser.domain.RoutePoint
import io.ticofab.androidgpxparser.parser.domain.Track
import io.ticofab.androidgpxparser.parser.domain.TrackPoint
import io.ticofab.androidgpxparser.parser.domain.TrackSegment
import io.ticofab.androidgpxparser.parser.domain.WayPoint

class GPXParser {
    @Throws(XmlPullParserException::class, IOException::class)
    fun parse(stream: InputStream) = stream.use {
        val parser = Xml.newPullParser()
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true)
        parser.setInput(it, null)
        parser.nextTag()
        readGpx(parser)
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readGpx(parser: XmlPullParser): Gpx {
        val wayPoints: MutableList<WayPoint> = ArrayList()
        val tracks: MutableList<Track> = ArrayList()
        val routes: MutableList<Route> = ArrayList()
        parser.require(XmlPullParser.START_TAG, namespace, TAG_GPX)
        val builder = Gpx.Builder()
        builder.setVersion(parser.getAttributeValue(namespace, TAG_VERSION))
        builder.setCreator(parser.getAttributeValue(namespace, TAG_CREATOR))
        while (loopMustContinue(parser.next())) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                TAG_METADATA -> builder.setMetadata(readMetadata(parser))
                TAG_WAY_POINT -> wayPoints.add(readWayPoint(parser))
                TAG_ROUTE -> routes.add(readRoute(parser))
                TAG_TRACK -> tracks.add(readTrack(parser))
                else -> skip(parser)
            }
        }
        parser.require(XmlPullParser.END_TAG, namespace, TAG_GPX)
        return builder
                .setWayPoints(wayPoints)
                .setRoutes(routes)
                .setTracks(tracks)
                .build()
    }

    // Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
    // to their respective "read" methods for processing. Otherwise, skips the tag.
    @Throws(XmlPullParserException::class, IOException::class)
    private fun readTrack(parser: XmlPullParser): Track {
        val trackBuilder = Track.Builder()
        val segments: MutableList<TrackSegment> = ArrayList()
        parser.require(XmlPullParser.START_TAG, namespace, TAG_TRACK)
        while (loopMustContinue(parser.next())) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                TAG_NAME -> trackBuilder.setTrackName(readName(parser))
                TAG_SEGMENT -> segments.add(readSegment(parser))
                TAG_DESC -> trackBuilder.setTrackDesc(readDesc(parser))
                TAG_CMT -> trackBuilder.setTrackCmt(readCmt(parser))
                TAG_SRC -> trackBuilder.setTrackSrc(readString(parser, TAG_SRC))
                TAG_LINK -> trackBuilder.setTrackLink(readLink(parser))
                TAG_NUMBER -> trackBuilder.setTrackNumber(readNumber(parser))
                TAG_TYPE -> trackBuilder.setTrackType(readString(parser, TAG_TYPE))
                else -> skip(parser)
            }
        }
        parser.require(XmlPullParser.END_TAG, namespace, TAG_TRACK)
        return trackBuilder
                .setTrackSegments(segments)
                .build()
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readLink(parser: XmlPullParser): Link {
        parser.require(XmlPullParser.START_TAG, namespace, TAG_LINK)
        val linkBuilder = Link.Builder()
        linkBuilder.setLinkHref(parser.getAttributeValue(namespace, TAG_HREF))
        while (loopMustContinue(parser.next())) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                TAG_TEXT -> linkBuilder.setLinkText(readString(parser, TAG_TEXT))
                TAG_TYPE -> linkBuilder.setLinkType(readString(parser, TAG_TYPE))
                else -> skip(parser)
            }
        }
        parser.require(XmlPullParser.END_TAG, namespace, TAG_LINK)
        return linkBuilder.build()
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readBounds(parser: XmlPullParser): Bounds {
        parser.require(XmlPullParser.START_TAG, namespace, TAG_BOUNDS)
        val bounds = Bounds.Builder()
                .setMinLat(java.lang.Double.valueOf(parser.getAttributeValue(namespace, TAG_MIN_LAT)))
                .setMinLon(java.lang.Double.valueOf(parser.getAttributeValue(namespace, TAG_MIN_LON)))
                .setMaxLat(java.lang.Double.valueOf(parser.getAttributeValue(namespace, TAG_MAX_LAT)))
                .setMaxLon(java.lang.Double.valueOf(parser.getAttributeValue(namespace, TAG_MAX_LON)))
                .build()
        parser.nextTag()
        parser.require(XmlPullParser.END_TAG, namespace, TAG_BOUNDS)
        return bounds
    }

    // Processes summary tags in the feed.
    @Throws(IOException::class, XmlPullParserException::class)
    private fun readSegment(parser: XmlPullParser): TrackSegment {
        val points: MutableList<TrackPoint> = ArrayList()
        parser.require(XmlPullParser.START_TAG, namespace, TAG_SEGMENT)
        while (loopMustContinue(parser.next())) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                TAG_TRACK_POINT -> points.add(readTrackPoint(parser))
                else -> skip(parser)
            }
        }
        parser.require(XmlPullParser.END_TAG, namespace, TAG_SEGMENT)
        return TrackSegment.Builder()
                .setTrackPoints(points)
                .build()
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readRoute(parser: XmlPullParser): Route {
        val points: MutableList<RoutePoint> = ArrayList()
        parser.require(XmlPullParser.START_TAG, namespace, TAG_ROUTE)
        val routeBuilder = Route.Builder()
        while (loopMustContinue(parser.next())) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                TAG_ROUTE_POINT -> points.add(readRoutePoint(parser))
                TAG_NAME -> routeBuilder.setRouteName(readName(parser))
                TAG_DESC -> routeBuilder.setRouteDesc(readDesc(parser))
                TAG_CMT -> routeBuilder.setRouteCmt(readCmt(parser))
                TAG_SRC -> routeBuilder.setRouteSrc(readString(parser, TAG_SRC))
                TAG_LINK -> routeBuilder.setRouteLink(readLink(parser))
                TAG_NUMBER -> routeBuilder.setRouteNumber(readNumber(parser))
                TAG_TYPE -> routeBuilder.setRouteType(readString(parser, TAG_TYPE))
                else -> skip(parser)
            }
        }
        parser.require(XmlPullParser.END_TAG, namespace, TAG_ROUTE)
        return routeBuilder
                .setRoutePoints(points)
                .build()
    }

    /**
     * Reads a single point, which can either be a [TrackPoint], [RoutePoint] or [WayPoint].
     *
     * @param builder The prepared builder, one of [TrackPoint.Builder], [RoutePoint.Builder] or [WayPoint.Builder].
     * @param parser  Parser
     * @param tagName Tag name, e.g. trkpt, rtept, wpt
     */
    @Throws(XmlPullParserException::class, IOException::class)
    private fun readPoint(builder: Point.Builder, parser: XmlPullParser, tagName: String): Point? {
        parser.require(XmlPullParser.START_TAG, namespace, tagName)
        builder.setLatitude(java.lang.Double.valueOf(parser.getAttributeValue(namespace, TAG_LAT)))
        builder.setLongitude(java.lang.Double.valueOf(parser.getAttributeValue(namespace, TAG_LON)))
        while (loopMustContinue(parser.next())) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                TAG_NAME -> builder.setName(readName(parser))
                TAG_DESC -> builder.setDesc(readDesc(parser))
                TAG_ELEVATION -> builder.setElevation(readElevation(parser))
                TAG_TIME -> builder.setTime(readTime(parser))
                TAG_TYPE -> builder.setType(readType(parser))
                else -> skip(parser)
            }
        }
        parser.require(XmlPullParser.END_TAG, namespace, tagName)
        return builder.build()
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readMetadata(parser: XmlPullParser): Metadata {
        val metadataBuilder = Metadata.Builder()
        parser.require(XmlPullParser.START_TAG, namespace, TAG_METADATA)
        while (loopMustContinue(parser.next())) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                TAG_NAME -> metadataBuilder.setName(readName(parser))
                TAG_DESC -> metadataBuilder.setDesc(readDesc(parser))
                TAG_AUTHOR -> metadataBuilder.setAuthor(readAuthor(parser))
                TAG_COPYRIGHT -> metadataBuilder.setCopyright(readCopyright(parser))
                TAG_LINK -> metadataBuilder.setLink(readLink(parser))
                TAG_TIME -> metadataBuilder.setTime(readTime(parser))
                TAG_KEYWORDS -> metadataBuilder.setKeywords(readString(parser, TAG_KEYWORDS))
                TAG_BOUNDS -> metadataBuilder.setBounds(readBounds(parser))
                TAG_EXTENSIONS -> skip(parser)
                else -> skip(parser)
            }
        }
        parser.require(XmlPullParser.END_TAG, namespace, TAG_METADATA)
        return metadataBuilder.build()
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readAuthor(parser: XmlPullParser): Author {
        val authorBuilder = Author.Builder()
        parser.require(XmlPullParser.START_TAG, namespace, TAG_AUTHOR)
        while (loopMustContinue(parser.next())) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                TAG_NAME -> authorBuilder.setName(readString(parser, TAG_NAME))
                TAG_EMAIL -> authorBuilder.setEmail(readEmail(parser))
                TAG_LINK -> authorBuilder.setLink(readLink(parser))
                else -> skip(parser)
            }
        }
        parser.require(XmlPullParser.END_TAG, namespace, TAG_AUTHOR)
        return authorBuilder.build()
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readEmail(parser: XmlPullParser): Email {
        parser.require(XmlPullParser.START_TAG, namespace, TAG_EMAIL)
        val emailBuilder = Email.Builder()
        emailBuilder.setId(parser.getAttributeValue(namespace, TAG_ID))
        emailBuilder.setDomain(parser.getAttributeValue(namespace, TAG_DOMAIN))

        // Email tag is self closed, advance the parser to next event
        parser.next()
        parser.require(XmlPullParser.END_TAG, namespace, TAG_EMAIL)
        return emailBuilder.build()
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readCopyright(parser: XmlPullParser): Copyright {
        parser.require(XmlPullParser.START_TAG, namespace, TAG_COPYRIGHT)
        val copyrightBuilder = Copyright.Builder()
        copyrightBuilder.setAuthor(parser.getAttributeValue(namespace, TAG_AUTHOR))
        while (loopMustContinue(parser.next())) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                TAG_YEAR -> copyrightBuilder.setYear(readYear(parser))
                TAG_LICENSE -> copyrightBuilder.setLicense(readString(parser, TAG_LICENSE))
                else -> skip(parser)
            }
        }
        parser.require(XmlPullParser.END_TAG, namespace, TAG_COPYRIGHT)
        return copyrightBuilder.build()
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readWayPoint(parser: XmlPullParser) =
            readPoint(WayPoint.Builder(), parser, TAG_WAY_POINT) as WayPoint

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readTrackPoint(parser: XmlPullParser) =
            readPoint(TrackPoint.Builder(), parser, TAG_TRACK_POINT) as TrackPoint

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readRoutePoint(parser: XmlPullParser) =
            readPoint(RoutePoint.Builder(), parser, TAG_ROUTE_POINT) as RoutePoint

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readName(parser: XmlPullParser) = readString(parser, TAG_NAME)

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readDesc(parser: XmlPullParser) = readString(parser, TAG_DESC)

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readType(parser: XmlPullParser) = readString(parser, TAG_TYPE)

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readCmt(parser: XmlPullParser) = readString(parser, TAG_CMT)

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readString(parser: XmlPullParser, tag: String): String {
        parser.require(XmlPullParser.START_TAG, namespace, tag)
        val value = readText(parser)
        parser.require(XmlPullParser.END_TAG, namespace, tag)
        return value
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readElevation(parser: XmlPullParser): Double {
        parser.require(XmlPullParser.START_TAG, namespace, TAG_ELEVATION)
        val ele = java.lang.Double.valueOf(readText(parser))
        parser.require(XmlPullParser.END_TAG, namespace, TAG_ELEVATION)
        return ele
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readTime(parser: XmlPullParser): DateTime {
        parser.require(XmlPullParser.START_TAG, namespace, TAG_TIME)
        val time = ISODateTimeFormat.dateTimeParser().parseDateTime(readText(parser))
        parser.require(XmlPullParser.END_TAG, namespace, TAG_TIME)
        return time
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readText(parser: XmlPullParser): String {
        var result = ""
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.text
            parser.nextTag()
        }
        return result
    }

    @Throws(IOException::class, XmlPullParserException::class, NumberFormatException::class)
    private fun readNumber(parser: XmlPullParser): Int {
        parser.require(XmlPullParser.START_TAG, namespace, TAG_NUMBER)
        val number = Integer.valueOf(readText(parser))
        parser.require(XmlPullParser.END_TAG, namespace, TAG_NUMBER)
        return number
    }

    @Throws(IOException::class, XmlPullParserException::class, NumberFormatException::class)
    private fun readYear(parser: XmlPullParser): Int {
        parser.require(XmlPullParser.START_TAG, namespace, TAG_YEAR)
        var yearStr = readText(parser)

        // we might need to strip an optional time-zone, even though I've never seen it
        // "2019" vs "2019+05:00" or "2019-03:00"
        var timeZoneStart = yearStr.indexOf('+')
        if (timeZoneStart == -1) timeZoneStart = yearStr.indexOf('-')
        yearStr = if (timeZoneStart == -1) yearStr else yearStr.substring(0, timeZoneStart)
        val year = Integer.valueOf(yearStr)
        parser.require(XmlPullParser.END_TAG, namespace, TAG_YEAR)
        return year
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun skip(parser: XmlPullParser) {
        check(parser.eventType == XmlPullParser.START_TAG)
        var depth = 1
        while (depth != 0) {
            when (parser.next()) {
                XmlPullParser.END_TAG -> depth--
                XmlPullParser.START_TAG -> depth++
            }
        }
    }

    private fun loopMustContinue(next: Int) =
            next != XmlPullParser.END_TAG && next != XmlPullParser.END_DOCUMENT

    companion object {
        private const val TAG_GPX = "gpx"
        private const val TAG_VERSION = "version"
        private const val TAG_CREATOR = "creator"
        private const val TAG_METADATA = "metadata"
        private const val TAG_TRACK = "trk"
        private const val TAG_SEGMENT = "trkseg"
        private const val TAG_TRACK_POINT = "trkpt"
        private const val TAG_LAT = "lat"
        private const val TAG_LON = "lon"
        private const val TAG_ELEVATION = "ele"
        private const val TAG_TIME = "time"
        private const val TAG_WAY_POINT = "wpt"
        private const val TAG_ROUTE = "rte"
        private const val TAG_ROUTE_POINT = "rtept"
        private const val TAG_NAME = "name"
        private const val TAG_DESC = "desc"
        private const val TAG_CMT = "cmt"
        private const val TAG_SRC = "src"
        private const val TAG_LINK = "link"
        private const val TAG_NUMBER = "number"
        private const val TAG_TYPE = "type"
        private const val TAG_TEXT = "text"
        private const val TAG_AUTHOR = "author"
        private const val TAG_COPYRIGHT = "copyright"
        private const val TAG_KEYWORDS = "keywords"
        private const val TAG_BOUNDS = "bounds"
        private const val TAG_EXTENSIONS = "extensions"
        private const val TAG_MIN_LAT = "minlat"
        private const val TAG_MIN_LON = "minlon"
        private const val TAG_MAX_LAT = "maxlat"
        private const val TAG_MAX_LON = "maxlon"
        private const val TAG_HREF = "href"
        private const val TAG_YEAR = "year"
        private const val TAG_LICENSE = "license"
        private const val TAG_EMAIL = "email"
        private const val TAG_ID = "id"
        private const val TAG_DOMAIN = "domain"
        private val namespace: String? = null
    }
}