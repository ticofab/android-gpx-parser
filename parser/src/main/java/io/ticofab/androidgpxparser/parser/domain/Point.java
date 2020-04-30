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
    private final Double mSpeed;
    private final Double mMagVar;
    private final Double mHdop;
    private final Double mVdop;
    private final Double mPdop;
    Point(Builder builder) {
        mLatitude = builder.mLatitude;
        mLongitude = builder.mLongitude;
        mElevation = builder.mElevation;
        mTime = builder.mTime;
        mName = builder.mName;
        mDesc = builder.mDesc;
        mType = builder.mType;
        mSpeed = builder.mSpeed;
        mMagVar = builder.mMagVar;
        mHdop = builder.mHdop;
        mVdop = builder.mVdop;
        mPdop = builder.mPdop;
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
     * @return the speed
     */
    public Double getSpeed() {
        return mSpeed;
    }

    /**
     * @return the Magnetic Variation
     */
    public Double getMagVar() {
        return mMagVar;
    }

    /**
     * @return the hdop
     */
    public Double getHdop() {
        return mHdop;
    }

    /**
     * @return the vdop
     */
    public Double getVdop() {
        return mVdop;
    }

    /**
     * @return the pdop
     */
    public Double getPdop() {
        return mPdop;
    }

    public static abstract class Builder {
        private Double mLatitude;
        private Double mLongitude;
        private Double mElevation;
        private DateTime mTime;
        private String mName;
        private String mDesc;
        private String mType;
        private Double mSpeed;
        private Double mMagVar;
        private Double mHdop;
        private Double mVdop;
        private Double mPdop;

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

        public Builder setSpeed(Double speed) {
            this.mSpeed = speed;
            return this;
        }

        public Builder setMagVar(Double magVar) {
            this.mMagVar = magVar;
            return this;
        }

        public Builder setHdop(Double hdop) {
            this.mHdop = hdop;
            return this;
        }

        public Builder setVdop(Double vdop) {
            this.mVdop = vdop;
            return this;
        }

        public Builder setPdop(Double pdop) {
            this.mPdop = pdop;
            return this;
        }

        public abstract Point build();
    }
}
