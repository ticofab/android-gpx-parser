package io.ticofab.androidgpxparser.parser.domain;

import com.google.android.gms.maps.model.LatLng;

import org.joda.time.DateTime;

public class TrackPoint {
    private final LatLng mLatLng;
    private final Double mElevation;
    private final DateTime mTime;

    public TrackPoint(Builder builder) {
        mLatLng = builder.mLatLng;
        mElevation = builder.mElevation;
        mTime = builder.mTime;
    }

    public Double getElevation() {
        return mElevation;
    }

    public LatLng getLatLng() {
        return mLatLng;
    }

    public DateTime getTime() {
        return mTime;
    }

    public static class Builder {
        private LatLng mLatLng;
        private Double mElevation;
        private DateTime mTime;

        public Builder setLatLng(LatLng latLng) {
            mLatLng = latLng;
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

        public TrackPoint build() {
            return new TrackPoint(this);
        }
    }

}
