package io.ticofab.androidgpxparser.parser.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gpx {
    private final String mVersion;
    private final String mCreator;
    private final Metadata mMetadata;
    private final List<WayPoint> mWayPoints;
    private final List<Route> mRoutes;
    private final List<Track> mTracks;

    private Gpx(Builder builder) {
        mVersion = builder.mVersion;
        mCreator = builder.mCreator;
        mMetadata = builder.mMetadata;
        mWayPoints = Collections.unmodifiableList(new ArrayList<>(builder.mWayPoints));
        mRoutes = Collections.unmodifiableList(new ArrayList<>(builder.mRoutes));
        mTracks = Collections.unmodifiableList(new ArrayList<>(builder.mTracks));
    }

    public String getVersion() { return mVersion; }

    public String getCreator() { return mCreator; }

    public Metadata getMetadata() {
        return mMetadata;
    }

    public List<WayPoint> getWayPoints() {
        return mWayPoints;
    }

    public List<Route> getRoutes() {
        return mRoutes;
    }

    public List<Track> getTracks() {
        return mTracks;
    }

    public static class Builder {
        private List<WayPoint> mWayPoints;
        private List<Route> mRoutes;
        private List<Track> mTracks;
        private String mVersion;
        private String mCreator;
        private Metadata mMetadata;

        public Builder setTracks(List<Track> tracks) {
            mTracks = tracks;
            return this;
        }

        public Builder setWayPoints(List<WayPoint> wayPoints) {
            mWayPoints = wayPoints;
            return this;
        }

        public Builder setRoutes(List<Route> routes) {
            this.mRoutes = routes;
            return this;
        }

        public Builder setVersion(String version) {
            mVersion = version;
            return this;
        }

        public Builder setCreator(String creator) {
            mCreator = creator;
            return this;
        }

        public Builder setMetadata(Metadata mMetadata) {
            this.mMetadata = mMetadata;
            return this;
        }

        public Gpx build() {
            return new Gpx(this);
        }


    }
}
