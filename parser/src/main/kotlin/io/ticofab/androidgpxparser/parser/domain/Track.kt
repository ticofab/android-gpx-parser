package io.ticofab.androidgpxparser.parser.domain

class Track private constructor(builder: Builder) {
    val trackName = builder.trackName
    val trackSegments = builder.trackSegments?.toList()
    val trackDesc = builder.trackDesc
    val trackCmt = builder.trackCmt
    val trackSrc = builder.trackSrc
    val trackNumber = builder.trackNumber
    val trackLink = builder.trackLink
    val trackType = builder.trackType

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

        fun setTrackName(trackName: String?) = apply { this.trackName = trackName }

        fun setTrackDesc(trackDesc: String?) = apply { this.trackDesc = trackDesc }

        fun setTrackSegments(trackSegments: List<TrackSegment>?) = apply { this.trackSegments = trackSegments }

        fun setTrackCmt(trackCmt: String?) = apply { this.trackCmt = trackCmt }

        fun setTrackSrc(trackSrc: String?) = apply { this.trackSrc = trackSrc }

        fun setTrackNumber(trackNumber: Int?) = apply { this.trackNumber = trackNumber }

        fun setTrackLink(link: Link?) = apply { trackLink = link }

        fun setTrackType(type: String?) = apply { trackType = type }

        fun build() = Track(this)
    }
}