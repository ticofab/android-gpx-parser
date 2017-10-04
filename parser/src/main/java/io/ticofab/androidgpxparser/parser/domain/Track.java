package io.ticofab.androidgpxparser.parser.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Track {
    private final String mTrackName;
    private final List<TrackSegment> mTrackSegments;
    private final String mTrackDesc;
    private final String mTrackCmt;
    private final String mTrackSrc;
    private final Integer mTrackNumber;
    private final Link mTrackLink;
    private final String mTrackType;

    private Track(Builder builder) {
        mTrackName = builder.mTrackName;
        mTrackDesc = builder.mTrackDesc;
        mTrackCmt = builder.mTrackCmt;
        mTrackSrc = builder.mTrackSrc;
        mTrackNumber = builder.mTrackNumber;
        mTrackSegments = Collections.unmodifiableList(new ArrayList<>(builder.mTrackSegments));
        mTrackLink = builder.mTrackLink;
        mTrackType = builder.mTrackType;
    }

    public String getTrackName() {
        return mTrackName;
    }

    public String getTrackDesc() {
        return mTrackDesc;
    }

    public String getTrackCmt() {
        return mTrackCmt;
    }

    public String getTrackSrc() {
        return mTrackSrc;
    }

    public Integer getTrackNumber() {
        return mTrackNumber;
    }

    public Link getTrackLink() {
        return mTrackLink;
    }

    public String getTrackType() {
        return mTrackType;
    }

    public List<TrackSegment> getTrackSegments() {
        return mTrackSegments;
    }

    public static class Builder {
        private String mTrackName;
        private List<TrackSegment> mTrackSegments;
        private String mTrackDesc;
        private String mTrackCmt;
        private String mTrackSrc;
        private Integer mTrackNumber;
        private Link mTrackLink;
        private String mTrackType;

        public Builder setTrackName(String trackName) {
            mTrackName = trackName;
            return this;
        }

        public Builder setTrackDesc(String trackDesc) {
            mTrackDesc = trackDesc;
            return this;
        }

        public Builder setTrackSegments(List<TrackSegment> trackSegments) {
            mTrackSegments = trackSegments;
            return this;
        }

        public Builder setTrackCmt(String trackCmt) {
            mTrackCmt = trackCmt;
            return this;
        }

        public Builder setTrackSrc(String trackSrc) {
            mTrackSrc = trackSrc;
            return this;
        }

        public Builder setTrackNumber(Integer trackNumber) {
            mTrackNumber = trackNumber;
            return this;
        }

        public Builder setTrackLink(Link link) {
            mTrackLink = link;
            return this;
        }

        public Builder setTrackType(String type) {
            mTrackType = type;
            return this;
        }

        public Track build() {
            return new Track(this);
        }


    }
}
