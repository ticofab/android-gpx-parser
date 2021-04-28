package io.ticofab.androidgpxparser.parser.domain

import org.joda.time.DateTime

/**
 * A point containing a location, time and name.
 */
abstract class Point internal constructor(builder: Builder) {
    /**
     * @return the latitude in degrees
     */
    val latitude: Double?

    /**
     * @return the longitude in degrees
     */
    val longitude: Double?

    /**
     * @return the elevation in meters
     */
    val elevation: Double?
    val time: DateTime?

    /**
     * @return the point name
     */
    val name: String?

    /**
     * @return the description
     */
    val desc: String?

    /**
     * @return the type (category)
     */
    val type: String?

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

        fun setLatitude(latitude: Double?): Builder {
            this.latitude = latitude
            return this
        }

        fun setLongitude(longitude: Double?): Builder {
            this.longitude = longitude
            return this
        }

        fun setElevation(elevation: Double?): Builder {
            this.elevation = elevation
            return this
        }

        fun setTime(time: DateTime?): Builder {
            this.time = time
            return this
        }

        fun setName(mame: String?): Builder {
            name = mame
            return this
        }

        fun setDesc(desc: String?): Builder {
            this.desc = desc
            return this
        }

        fun setType(type: String?): Builder {
            this.type = type
            return this
        }

        abstract fun build(): Point
    }

    init {
        latitude = builder.latitude
        longitude = builder.longitude
        elevation = builder.elevation
        time = builder.time
        name = builder.name
        desc = builder.desc
        type = builder.type
    }
}