package io.ticofab.androidgpxparser.parser.domain;

import org.joda.time.DateTime;

/**
 * A point containing a location, time and name.
 *
 */
public abstract class Point {
    private final Double mLatitude;
    private final Double mLongitude;
    private final Double mElevation;
    private final DateTime mTime;
    private String mName;

    Point(Builder builder) {
        mLatitude = builder.mLatitude;
        mLongitude = builder.mLongitude;
        mElevation = builder.mElevation;
        mTime = builder.mTime;
        mName = builder.mName;
    }

    /**
     * @return the latitude in degrees
     */
    public Double getLatitude() {
        return mLatitude;
    }

    /**
     * @return the longitude in degrees
     */
    public Double getLongitude() {
        return mLongitude;
    }

    /**
     * @return the elevation in meters
     */
    public Double getElevation() {
        return mElevation;
    }

    public DateTime getTime() {
        return mTime;
    }

    /**
     * @return the point name
     */
    public String getName() {
        return mName;
    }

    public static abstract class Builder {
        private Double mLatitude;
        private Double mLongitude;
        private Double mElevation;
        private DateTime mTime;
        private String mName;

        public Builder setLatitude(Double latitude) {
            mLatitude = latitude;
            return this;
        }

        public Builder setLongitude(Double longitude) {
            mLongitude = longitude;
            return this;
        }

        public Builder setElevation(Double elevation) {
            mElevation = elevation;
            return this;
        }

        public Builder setTime(DateTime time) {
            mTime = time;
            return this;
        }

        public Builder setName(String mName) {
            this.mName = mName;
            return this;
        }

        public abstract Point build();
    }
}
