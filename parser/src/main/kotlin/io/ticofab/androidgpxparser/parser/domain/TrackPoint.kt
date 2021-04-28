package io.ticofab.androidgpxparser.parser.domain

/**
 * A track point (trkpt) element.
 */
class TrackPoint private constructor(builder: Builder) : Point(builder) {
    class Builder : Point.Builder() {
        override fun build(): TrackPoint {
            return TrackPoint(this)
        }
    }
}