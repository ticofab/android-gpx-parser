package io.ticofab.androidgpxparser.parser.domain;

import org.joda.time.DateTime;

/**
 * A point containing a location, time and name.
 */
public abstract class Point {
    private final Double mLatitude;
    private final Double mLongitude;
    private final Double mElevation;
    private final DateTime mTime;
    private final String mName;
    private final String mDesc;
    private final String mType;

    Point(Builder builder) {
        mLatitude = builder.getLatitude();
        mLongitude = builder.getLongitude();
        mElevation = builder.getElevation();
        mTime = builder.getTime();
        mName = builder.getName();
        mDesc = builder.getDesc();
        mType = builder.getType();
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

    /**
     * @return the description
     */
    public String getDesc() {
        return mDesc;
    }

    /**
     * @return the type (category)
     */
    public String getType() {
        return mType;
    }

    public static abstract class Builder {
        private Double mLatitude;
        private Double mLongitude;
        private Double mElevation;
        private DateTime mTime;
        private String mName;
        private String mDesc;
        private String mType;

        public Double getLatitude() { return mLatitude; }

        public Double getLongitude() { return mLongitude; }

        public Double getElevation() { return mElevation; }

        public DateTime getTime() { return mTime; }

        public String getName() { return mName; }

        public String getDesc() { return mDesc; }

        public String getType() { return mType; }

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

        public Builder setName(String mame) {
            mName = mame;
            return this;
        }

        public Builder setDesc(String desc) {
            mDesc = desc;
            return this;
        }

        public Builder setType(String type) {
            mType = type;
            return this;
        }

        public abstract Point build();
    }
}
