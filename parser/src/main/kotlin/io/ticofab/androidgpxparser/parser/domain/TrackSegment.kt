package io.ticofab.androidgpxparser.parser.domain

class TrackSegment private constructor(builder: Builder) {
    val trackPoints = builder.trackPoints?.toList()

    class Builder {
        var trackPoints: List<TrackPoint>? = null
            private set

        fun setTrackPoints(trackPoints: List<TrackPoint>?) = apply { this.trackPoints = trackPoints }

        fun build() = TrackSegment(this)
    }
}