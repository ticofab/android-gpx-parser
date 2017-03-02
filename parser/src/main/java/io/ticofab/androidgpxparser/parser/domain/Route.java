package io.ticofab.androidgpxparser.parser.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Route {
    private final List<RoutePoint> mRoutePoints;

    private Route(Builder builder) {
        mRoutePoints = Collections.unmodifiableList(new ArrayList<>(builder.mRoutePoints));
    }

    public List<RoutePoint> getRoutePoints() {
        return mRoutePoints;
    }

    public static class Builder {
        private List<RoutePoint> mRoutePoints;

        public Builder setRoutePoints(List<RoutePoint> routePoints) {
            mRoutePoints = routePoints;
            return this;
        }

        public Route build() {
            return new Route(this);
        }
    }
}
