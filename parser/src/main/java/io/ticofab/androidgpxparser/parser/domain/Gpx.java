package io.ticofab.androidgpxparser.parser.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gpx {
    private final List<WayPoint> mWayPoints;
    private final List<Route> mRoutes;
    private final List<Track> mTracks;

    private Gpx(Builder builder) {
        mWayPoints = Collections.unmodifiableList(new ArrayList<>(builder.mWayPoints));
        mRoutes = Collections.unmodifiableList(new ArrayList<>(builder.mRoutes));
        mTracks = Collections.unmodifiableList(new ArrayList<>(builder.mTracks));
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

        public Gpx build() {
            return new Gpx(this);
        }
    }
}
