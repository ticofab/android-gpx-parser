package io.ticofab.androidgpxparser.parser.domain

/**
 * A route point (rtept) element.
 */
class RoutePoint private constructor(builder: Builder) : Point(builder) {
    class Builder : Point.Builder() {
        override fun build() = RoutePoint(this)
    }
}