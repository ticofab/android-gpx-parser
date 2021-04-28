package io.ticofab.androidgpxparser.parser.domain

class Route private constructor(builder: Builder) {
    val routePoints = builder.routePoints?.toList()
    val routeName = builder.routeName
    val routeDesc = builder.routeDesc
    val routeCmt = builder.routeCmt
    val routeSrc = builder.routeSrc
    val routeNumber = builder.routeNumber
    val routeLink = builder.routeLink
    val routeType = builder.routeType

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

        fun setRoutePoints(routePoints: List<RoutePoint>?) = apply { this.routePoints = routePoints }

        fun setRouteName(routeName: String?) = apply { this.routeName = routeName }

        fun setRouteDesc(routeDesc: String?) = apply { this.routeDesc = routeDesc }

        fun setRouteCmt(routeCmt: String?) = apply { this.routeCmt = routeCmt }

        fun setRouteSrc(routeSrc: String?) = apply { this.routeSrc = routeSrc }

        fun setRouteNumber(routeNumber: Int?) = apply { this.routeNumber = routeNumber }

        fun setRouteLink(routeLink: Link?) = apply { this.routeLink = routeLink }

        fun setRouteType(routeType: String?) = apply { this.routeType = routeType }

        fun build() = Route(this)
    }
}