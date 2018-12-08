package io.ticofab.androidgpxparser.parser.domain;

public class Bounds {

    private final Double mMinLat;
    private final Double mMinLon;
    private final Double mMaxLat;
    private final Double mMaxLon;

    private Bounds(Builder builder) {
        mMinLat = builder.mMinLat;
        mMinLon = builder.mMinLon;
        mMaxLat = builder.mMaxLat;
        mMaxLon = builder.mMaxLon;
    }

    public Double getMinLat() {
        return mMinLat;
    }

    public Double getMinLon() {
        return mMinLon;
    }

    public Double getMaxLat() {
        return mMaxLat;
    }

    public Double getMaxLon() {
        return mMaxLon;
    }

    public static class Builder {
        private Double mMinLat;
        private Double mMinLon;
        private Double mMaxLat;
        private Double mMaxLon;

        public Builder setMinLat(Double minLat) {
            mMinLat = minLat;
            return this;
        }

        public Builder setMinLon(Double minLon) {
            mMinLon = minLon;
            return this;
        }

        public Builder setMaxLat(Double maxLat) {
            mMaxLat = maxLat;
            return this;
        }

        public Builder setMaxLon(Double maxLon) {
            mMaxLon = maxLon;
            return this;
        }

        public Bounds build() {
            return new Bounds(this);
        }
    }
}
