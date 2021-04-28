package io.ticofab.androidgpxparser.parser.domain

import java.util.*

class Gpx private constructor(builder: Builder) {
    val version: String?
    val creator: String?
    val metadata: Metadata?
    val wayPoints: List<WayPoint>
    val routes: List<Route>
    val tracks: List<Track>

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

        fun setTracks(tracks: List<Track>?): Builder {
            this.tracks = tracks
            return this
        }

        fun setWayPoints(wayPoints: List<WayPoint>?): Builder {
            this.wayPoints = wayPoints
            return this
        }

        fun setRoutes(routes: List<Route>?): Builder {
            this.routes = routes
            return this
        }

        fun setVersion(version: String?): Builder {
            this.version = version
            return this
        }

        fun setCreator(creator: String?): Builder {
            this.creator = creator
            return this
        }

        fun setMetadata(mMetadata: Metadata?): Builder {
            metadata = mMetadata
            return this
        }

        fun build(): Gpx {
            return Gpx(this)
        }
    }

    init {
        version = builder.version
        creator = builder.creator
        metadata = builder.metadata
        wayPoints = Collections.unmodifiableList(ArrayList(builder.wayPoints))
        routes = Collections.unmodifiableList(ArrayList(builder.routes))
        tracks = Collections.unmodifiableList(ArrayList(builder.tracks))
    }
}