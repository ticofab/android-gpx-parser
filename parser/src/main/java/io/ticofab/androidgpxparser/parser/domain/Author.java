package io.ticofab.androidgpxparser.parser.domain;

public class Author {

    private final String mName;
    private final Email mEmail;
    private final Link mLink;

    private Author(Builder builder) {
        this.mName = builder.mName;
        this.mEmail = builder.mEmail;
        this.mLink = builder.mLink;
    }

    public String getName() {
        return mName;
    }

    public Email getEmail() {
        return mEmail;
    }

    public Link getLink() {
        return mLink;
    }

    public static class Builder {
        private String mName;
        private Email mEmail;
        private Link mLink;

        public Builder setName(String mName) {
            this.mName = mName;
            return this;
        }

        public Builder setEmail(Email mEmail) {
            this.mEmail = mEmail;
            return this;
        }

        public Builder setLink(Link mLink) {
            this.mLink = mLink;
            return this;
        }

        public Author build() {
            return new Author(this);
        }
    }
}
