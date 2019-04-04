package io.ticofab.androidgpxparser.parser.domain;

public class Copyright {

    private final String mAuthor;
    private final String mYear;
    private final String mLicense;

    private Copyright(Builder builder) {
        this.mAuthor = builder.mAuthor;
        this.mYear = builder.mYear;
        this.mLicense = builder.mLicense;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getYear() {
        return mYear;
    }

    public String getLicense() {
        return mLicense;
    }

    public static class Builder {
        private String mAuthor;
        private String mYear;
        private String mLicense;

        public Builder setAuthor(String mAuthor) {
            this.mAuthor = mAuthor;
            return this;
        }

        public Builder setYear(String mYear) {
            this.mYear = mYear;
            return this;
        }

        public Builder setLicense(String mLicense) {
            this.mLicense = mLicense;
            return this;
        }

        public Copyright build() {
            return new Copyright(this);
        }
    }
}
