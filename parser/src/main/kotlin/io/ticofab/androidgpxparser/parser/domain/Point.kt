package io.ticofab.androidgpxparser.parser.domain

import org.joda.time.DateTime

/**
 * A point containing a location, time and name.
 */
abstract class Point internal constructor(builder: Builder) {
    /**
     * @return the latitude in degrees
     */
    val latitude = builder.latitude

    /**
     * @return the longitude in degrees
     */
    val longitude = builder.longitude

    /**
     * @return the elevation in meters
     */
    val elevation = builder.elevation
    val time = builder.time

    /**
     * @return the point name
     */
    val name = builder.name

    /**
     * @return the description
     */
    val desc = builder.desc

    /**
     * @return the type (category)
     */
    val type = builder.type

    abstract class Builder {
        var latitude: Double? = null
            private set
        var longitude: Double? = null
            private set
        var elevation: Double? = null
            private set
        var time: DateTime? = null
            private set
        var name: String? = null
            private set
        var desc: String? = null
            private set
        var type: String? = null
            private set

        fun setLatitude(latitude: Double?) = apply { this.latitude = latitude }

        fun setLongitude(longitude: Double?) = apply { this.longitude = longitude }

        fun setElevation(elevation: Double?) = apply { this.elevation = elevation }

        fun setTime(time: DateTime?) = apply { this.time = time }

        fun setName(mame: String?) = apply { name = mame }

        fun setDesc(desc: String?) = apply { this.desc = desc }

        fun setType(type: String?) = apply { this.type = type }

        abstract fun build(): Point
    }
}