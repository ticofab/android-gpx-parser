package io.ticofab.androidgpxparser.parser.domain

class Bounds private constructor(builder: Builder) {
    val minLat: Double?
    val minLon: Double?
    val maxLat: Double?
    val maxLon: Double?

    class Builder {
        var minLat: Double? = null
            private set
        var minLon: Double? = null
            private set
        var maxLat: Double? = null
            private set
        var maxLon: Double? = null
            private set

        fun setMinLat(minLat: Double?): Builder {
            this.minLat = minLat
            return this
        }

        fun setMinLon(minLon: Double?): Builder {
            this.minLon = minLon
            return this
        }

        fun setMaxLat(maxLat: Double?): Builder {
            this.maxLat = maxLat
            return this
        }

        fun setMaxLon(maxLon: Double?): Builder {
            this.maxLon = maxLon
            return this
        }

        fun build(): Bounds {
            return Bounds(this)
        }
    }

    init {
        minLat = builder.minLat
        minLon = builder.minLon
        maxLat = builder.maxLat
        maxLon = builder.maxLon
    }
}