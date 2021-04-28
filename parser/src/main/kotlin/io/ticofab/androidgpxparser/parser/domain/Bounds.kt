package io.ticofab.androidgpxparser.parser.domain

class Bounds private constructor(builder: Builder) {
    val minLat = builder.minLat
    val minLon = builder.minLon
    val maxLat = builder.maxLat
    val maxLon = builder.maxLon

    class Builder {
        var minLat: Double? = null
            private set
        var minLon: Double? = null
            private set
        var maxLat: Double? = null
            private set
        var maxLon: Double? = null
            private set

        fun setMinLat(minLat: Double?) = apply { this.minLat = minLat }

        fun setMinLon(minLon: Double?) = apply { this.minLon = minLon }

        fun setMaxLat(maxLat: Double?) = apply { this.maxLat = maxLat }

        fun setMaxLon(maxLon: Double?) = apply { this.maxLon = maxLon }

        fun build() = Bounds(this)
    }
}