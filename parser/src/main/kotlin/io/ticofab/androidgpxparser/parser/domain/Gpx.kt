package io.ticofab.androidgpxparser.parser.domain

class Gpx private constructor(builder: Builder) {
    val version = builder.version
    val creator = builder.creator
    val metadata = builder.metadata
    val wayPoints = builder.wayPoints?.toList()
    val routes = builder.routes?.toList()
    val tracks = builder.tracks?.toList()

    class Builder {
        var wayPoints: List<WayPoint>? = null
            private set
        var routes: List<Route>? = null
            private set
        var tracks: List<Track>? = null
            private set
        var version: String? = null
            private set
        var creator: String? = null
            private set
        var metadata: Metadata? = null
            private set

        fun setTracks(tracks: List<Track>?) = apply { this.tracks = tracks }

        fun setWayPoints(wayPoints: List<WayPoint>?) = apply { this.wayPoints = wayPoints }

        fun setRoutes(routes: List<Route>?) = apply { this.routes = routes }

        fun setVersion(version: String?) = apply { this.version = version }

        fun setCreator(creator: String?) = apply { this.creator = creator }

        fun setMetadata(mMetadata: Metadata?) = apply { metadata = mMetadata }

        fun build() = Gpx(this)
    }
}