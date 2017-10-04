package io.ticofab.androidgpxparser.parser.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Route {
    private final List<RoutePoint> mRoutePoints;
    private final String mRouteName;
    private final String mRouteDesc;
    private final String mRouteCmt;
    private final String mRouteSrc;
    private final Integer mRouteNumber;
    private final Link mRouteLink;
    private final String mRouteType;

    private Route(Builder builder) {
        mRoutePoints = Collections.unmodifiableList(new ArrayList<>(builder.mRoutePoints));
        mRouteName = builder.mRouteName;
        mRouteDesc = builder.mRouteDesc;
        mRouteCmt = builder.mRouteCmt;
        mRouteSrc = builder.mRouteSrc;
        mRouteNumber = builder.mRouteNumber;
        mRouteLink = builder.mRouteLink;
        mRouteType = builder.mRouteType;
    }

    public List<RoutePoint> getRoutePoints() {
        return mRoutePoints;
    }

    public String getRouteName() {
        return mRouteName;
    }

    public String getRouteDesc() {
        return mRouteDesc;
    }

    public String getRouteCmt() {
        return mRouteCmt;
    }

    public String getRouteSrc() {
        return mRouteSrc;
    }

    public Integer getRouteNumber() {
        return mRouteNumber;
    }

    public Link getRouteLink() {
        return mRouteLink;
    }

    public String getRouteType() {
        return mRouteType;
    }

    public static class Builder {
        private List<RoutePoint> mRoutePoints;
        private String mRouteName;
        private String mRouteDesc;
        private String mRouteCmt;
        private String mRouteSrc;
        private Integer mRouteNumber;
        private Link mRouteLink;
        private String mRouteType;

        public Builder setRoutePoints(List<RoutePoint> routePoints) {
            mRoutePoints = routePoints;
            return this;
        }

        public Builder setRouteName(String mRouteName) {
            this.mRouteName = mRouteName;
            return this;
        }

        public Builder setRouteDesc(String mRouteDesc) {
            this.mRouteDesc = mRouteDesc;
            return this;
        }

        public Builder setRouteCmt(String mRouteCmt) {
            this.mRouteCmt = mRouteCmt;
            return this;
        }

        public Builder setRouteSrc(String mRouteSrc) {
            this.mRouteSrc = mRouteSrc;
            return this;
        }

        public Builder setRouteNumber(Integer mRouteNumber) {
            this.mRouteNumber = mRouteNumber;
            return this;
        }

        public Builder setRouteLink(Link mRouteLink) {
            this.mRouteLink = mRouteLink;
            return this;
        }

        public Builder setRouteType(String mRouteType) {
            this.mRouteType = mRouteType;
            return this;
        }

        public Route build() {
            return new Route(this);
        }
    }
}
