package io.ticofab.androidgpxparser.parser.domain;

/**
 * Created by Stu Stirling on 04/10/2017.
 */

public class Link {

    private final String mLinkText;
    private final String mLinkType;

    private Link(Builder builder) {
        mLinkText = builder.mLinkText;
        mLinkType = builder.mLinkType;
    }

    public String getText() {
        return mLinkText;
    }

    public String getType() {
        return mLinkType;
    }

    public static class Builder {
        private String mLinkText;
        private String mLinkType;

        public Builder() {

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
