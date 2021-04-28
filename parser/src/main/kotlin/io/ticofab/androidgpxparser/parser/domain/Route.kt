package io.ticofab.androidgpxparser.parser.domain

import java.util.*

class Route private constructor(builder: Builder) {
    val routePoints: List<RoutePoint>
    val routeName: String?
    val routeDesc: String?
    val routeCmt: String?
    val routeSrc: String?
    val routeNumber: Int?
    val routeLink: Link?
    val routeType: String?

    class Builder {
        var routePoints: List<RoutePoint>? = null
            private set
        var routeName: String? = null
            private set
        var routeDesc: String? = null
            private set
        var routeCmt: String? = null
            private set
        var routeSrc: String? = null
            private set
        var routeNumber: Int? = null
            private set
        var routeLink: Link? = null
            private set
        var routeType: String? = null
            private set

        fun setRoutePoints(routePoints: List<RoutePoint>?): Builder {
            this.routePoints = routePoints
            return this
        }

        fun setRouteName(routeName: String?): Builder {
            this.routeName = routeName
            return this
        }

        fun setRouteDesc(routeDesc: String?): Builder {
            this.routeDesc = routeDesc
            return this
        }

        fun setRouteCmt(routeCmt: String?): Builder {
            this.routeCmt = routeCmt
            return this
        }

        fun setRouteSrc(routeSrc: String?): Builder {
            this.routeSrc = routeSrc
            return this
        }

        fun setRouteNumber(routeNumber: Int?): Builder {
            this.routeNumber = routeNumber
            return this
        }

        fun setRouteLink(routeLink: Link?): Builder {
            this.routeLink = routeLink
            return this
        }

        fun setRouteType(routeType: String?): Builder {
            this.routeType = routeType
            return this
        }

        fun build(): Route {
            return Route(this)
        }
    }

    init {
        routePoints = Collections.unmodifiableList(ArrayList(builder.routePoints))
        routeName = builder.routeName
        routeDesc = builder.routeDesc
        routeCmt = builder.routeCmt
        routeSrc = builder.routeSrc
        routeNumber = builder.routeNumber
        routeLink = builder.routeLink
        routeType = builder.routeType
    }
}