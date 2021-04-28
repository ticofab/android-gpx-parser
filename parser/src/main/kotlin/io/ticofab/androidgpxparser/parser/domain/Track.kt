package io.ticofab.androidgpxparser.parser.domain

import java.util.*

class Track private constructor(builder: Builder) {
    val trackName: String?
    val trackSegments: List<TrackSegment>
    val trackDesc: String?
    val trackCmt: String?
    val trackSrc: String?
    val trackNumber: Int?
    val trackLink: Link?
    val trackType: String?

    class Builder {
        var trackName: String? = null
            private set
        var trackSegments: List<TrackSegment>? = null
            private set
        var trackDesc: String? = null
            private set
        var trackCmt: String? = null
            private set
        var trackSrc: String? = null
            private set
        var trackNumber: Int? = null
            private set
        var trackLink: Link? = null
            private set
        var trackType: String? = null
            private set

        fun setTrackName(trackName: String?): Builder {
            this.trackName = trackName
            return this
        }

        fun setTrackDesc(trackDesc: String?): Builder {
            this.trackDesc = trackDesc
            return this
        }

        fun setTrackSegments(trackSegments: List<TrackSegment>?): Builder {
            this.trackSegments = trackSegments
            return this
        }

        fun setTrackCmt(trackCmt: String?): Builder {
            this.trackCmt = trackCmt
            return this
        }

        fun setTrackSrc(trackSrc: String?): Builder {
            this.trackSrc = trackSrc
            return this
        }

        fun setTrackNumber(trackNumber: Int?): Builder {
            this.trackNumber = trackNumber
            return this
        }

        fun setTrackLink(link: Link?): Builder {
            trackLink = link
            return this
        }

        fun setTrackType(type: String?): Builder {
            trackType = type
            return this
        }

        fun build(): Track {
            return Track(this)
        }
    }

    init {
        trackName = builder.trackName
        trackDesc = builder.trackDesc
        trackCmt = builder.trackCmt
        trackSrc = builder.trackSrc
        trackNumber = builder.trackNumber
        trackSegments = Collections.unmodifiableList(ArrayList(builder.trackSegments))
        trackLink = builder.trackLink
        trackType = builder.trackType
    }
}