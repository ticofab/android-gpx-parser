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
    private final String mSym;
    private final String mCmt;
    private final Extensions mExtensions;

    Point(Builder builder) {
        mLatitude = builder.mLatitude;
        mLongitude = builder.mLongitude;
        mElevation = builder.mElevation;
        mTime = builder.mTime;
        mName = builder.mName;
        mDesc = builder.mDesc;
        mType = builder.mType;
        mSym = builder.mSym;
        mCmt = builder.mCmt;
        mExtensions = builder.mExtensions;
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

    /**
     * @return the point symbol
     */
    public String getSym() {
        return mSym;
    }

    /**
     * @return the point cmt
     */
    public String getCmt() {
        return mCmt;
    }

    /**
     * @return the extensions
     */
    public Extensions getExtensions() {
        return mExtensions;
    }

    public static abstract class Builder {
        private Double mLatitude;
        private Double mLongitude;
        private Double mElevation;
        private DateTime mTime;
        private String mName;
        private String mDesc;
        private String mType;
        private String mSym;
        private String mCmt;
        private Extensions mExtensions;

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

        public Builder setSym(String sym) {
            mSym = sym;
            return this;
        }
        public Builder setCmt(String cmt) {
            mCmt = cmt;
            return this;
        }

        public Builder setExtensions(Extensions extensions) {
            mExtensions = extensions;
            return this;
        }

        public abstract Point build();
    }
}
