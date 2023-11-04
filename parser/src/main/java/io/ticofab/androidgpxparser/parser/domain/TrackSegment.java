package io.ticofab.androidgpxparser.parser.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TrackSegment {
    private final List<TrackPoint> mTrackPoints;
    private final Map<String, Object> mExtensions;

    private TrackSegment(Builder builder) {
        mTrackPoints = Collections.unmodifiableList(new ArrayList<>(builder.mTrackPoints));
        mExtensions = Collections.unmodifiableMap(builder.mExtensions);
    }

    public List<TrackPoint> getTrackPoints() {
        return mTrackPoints;
    }

    public Map<String, Object> getExtensions() {
        return mExtensions;
    }

    public static class Builder {
        private List<TrackPoint> mTrackPoints;
        private Map<String, Object> mExtensions;

        public Builder setTrackPoints(List<TrackPoint> trackPoints) {
            mTrackPoints = trackPoints;
            return this;
        }

        public Builder setExtensions(Map<String, Object> extensions) {
            mExtensions = extensions;
            return this;
        }

        public TrackSegment build() {
            return new TrackSegment(this);
        }
    }
}
