package io.ticofab.androidgpxparser.parser.domain;

public class Copyright {

    private final String mAuthor;
    private final Integer mYear;
    private final String mLicense;

    private Copyright(Builder builder) {
        mAuthor = builder.mAuthor;
        mYear = builder.mYear;
        mLicense = builder.mLicense;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public Integer getYear() {
        return mYear;
    }

    public String getLicense() {
        return mLicense;
    }

    public static class Builder {
        private String mAuthor;
        private Integer mYear;
        private String mLicense;

        public Builder setAuthor(String author) {
            mAuthor = author;
            return this;
        }

        public Builder setYear(Integer year) {
            mYear = year;
            return this;
        }

        public Builder setLicense(String license) {
            mLicense = license;
            return this;
        }

        public Copyright build() {
            return new Copyright(this);
        }
    }
}
