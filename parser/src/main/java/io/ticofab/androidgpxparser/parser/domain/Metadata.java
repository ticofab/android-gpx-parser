package io.ticofab.androidgpxparser.parser.domain;

import org.joda.time.DateTime;

public class Metadata {

    private final String mName;
    private final String mDesc;
    private final Author mAuthor;
    private final Copyright mCopyright;
    private final Link mLink;
    private final DateTime mTime;
    private final String mKeywords;
    private final Bounds mBounds;
    private final String mExtensions;

    private Metadata(Metadata.Builder builder) {
        mName = builder.mName;
        mDesc = builder.mDesc;
        mAuthor = builder.mAuthor;
        mCopyright = builder.mCopyright;
        mLink = builder.mLink;
        mTime = builder.mTime;
        mKeywords = builder.mKeywords;
        mBounds = builder.mBounds;
        mExtensions = builder.mExtensions;
    }

    public String getName() {
        return mName;
    }

    public String getDesc() {
        return mDesc;
    }

    public Author getAuthor() {
        return mAuthor;
    }

    public Copyright getCopyright() {
        return mCopyright;
    }

    public Link getLink() {
        return mLink;
    }

    public DateTime getTime() {
        return mTime;
    }

    public String getKeywords() {
        return mKeywords;
    }

    public Bounds getBounds() {
        return mBounds;
    }

    public String getExtensions() {
        return mExtensions;
    }

    public static class Builder {
        private String mName;
        private String mDesc;
        private Author mAuthor;
        private Copyright mCopyright;
        private Link mLink;
        private DateTime mTime;
        private String mKeywords;
        private Bounds mBounds;
        private String mExtensions;

        public Builder setName(String name) {
            mName = name;
            return this;
        }

        public Builder setDesc(String desc) {
            mDesc = desc;
            return this;
        }

        public Builder setAuthor(Author author) {
            mAuthor = author;
            return this;
        }

        public Builder setCopyright(Copyright copyright) {
            mCopyright = copyright;
            return this;
        }

        public Builder setLink(Link link) {
            mLink = link;
            return this;
        }

        public Builder setTime(DateTime time) {
            mTime = time;
            return this;
        }

        public Builder setKeywords(String keywords) {
            mKeywords = keywords;
            return this;
        }

        public Builder setBounds(Bounds bounds) {
            mBounds = bounds;
            return this;
        }

        public Metadata build() {
            return new Metadata(this);
        }


    }
}
