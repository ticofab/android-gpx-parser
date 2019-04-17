package io.ticofab.androidgpxparser.parser.domain;

public class Email {

    private String mId;
    private String mDomain;

    private Email(Builder builder) {
        this.mId = builder.mId;
        this.mDomain = builder.mDomain;
    }

    public String getId() {
        return mId;
    }

    public String getDomain() {
        return mDomain;
    }

    public static class Builder {
        private String mId;
        private String mDomain;

        public Builder setId(String mId) {
            this.mId = mId;
            return this;
        }

        public Builder setDomain(String mDomain) {
            this.mDomain = mDomain;
            return this;
        }

        public Email build() {
            return new Email(this);
        }
    }
}
