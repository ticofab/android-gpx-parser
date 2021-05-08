package io.ticofab.androidgpxparser.parser.domain;

/**
 * Created by Stu Stirling on 04/10/2017.
 */

public class Link {

    private final String mLinkHref;
    private final String mLinkText;
    private final String mLinkType;

    private Link(Builder builder) {
        mLinkHref = builder.getLinkHref();
        mLinkText = builder.getLinkText();
        mLinkType = builder.getLinkType();
    }

    public String getHref() {
        return mLinkHref;
    }

    public String getText() {
        return mLinkText;
    }

    public String getType() {
        return mLinkType;
    }

    public static class Builder {
        private String mLinkHref;
        private String mLinkText;
        private String mLinkType;

        public Builder() {

        }

        public String getLinkHref() { return mLinkHref; }

        public String getLinkText() { return mLinkText; }

        public String getLinkType() { return mLinkType; }

        public Builder setLinkHref(String linkHref) {
            mLinkHref = linkHref;
            return this;
        }

        public Builder setLinkText(String linkText) {
            mLinkText = linkText;
            return this;
        }

        public Builder setLinkType(String linkType ) {
            mLinkType = linkType;
            return this;
        }

        public Link build(){
            return new Link(this);
        }
    }
}
