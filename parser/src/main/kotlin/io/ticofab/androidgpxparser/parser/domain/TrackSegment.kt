package io.ticofab.androidgpxparser.parser.domain

import java.util.*

class TrackSegment private constructor(builder: Builder) {
    val trackPoints: List<TrackPoint>

    class Builder {
        var trackPoints: List<TrackPoint>? = null
            private set

        fun setTrackPoints(trackPoints: List<TrackPoint>?): Builder {
            this.trackPoints = trackPoints
            return this
        }

        fun build(): TrackSegment {
            return TrackSegment(this)
        }
    }

    init {
        trackPoints = Collections.unmodifiableList(ArrayList(builder.trackPoints))
    }
}