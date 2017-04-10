package io.ticofab.androidgpxparser.parser.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Track {
    private final String mTrackName;
    private final List<TrackSegment> mTrackSegments;

    private Track(Builder builder) {
        mTrackName = builder.mTrackName;
        mTrackSegments = Collections.unmodifiableList(new ArrayList<>(builder.mTrackSegments));
    }

    public String getTrackName() {
        return mTrackName;
    }

    public List<TrackSegment> getTrackSegments() {
        return mTrackSegments;
    }

    public static class Builder {
        private String mTrackName;
        private List<TrackSegment> mTrackSegments;

        public Builder setTrackName(String trackName) {
            mTrackName = trackName;
            return this;
        }

        public Builder setTrackSegments(List<TrackSegment> trackSegments) {
            mTrackSegments = trackSegments;
            return this;
        }

        public Track build() {
            return new Track(this);
        }
    }
}
