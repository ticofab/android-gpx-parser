package io.ticofab.androidgpxparser.parser.domain

class Copyright private constructor(builder: Builder) {
    val author = builder.author
    val year = builder.year
    val license = builder.license

    class Builder {
        var author: String? = null
            private set
        var year: Int? = null
            private set
        var license: String? = null
            private set

        fun setAuthor(author: String?) = apply { this.author = author }

        fun setYear(year: Int?) = apply { this.year = year }

        fun setLicense(license: String?) = apply { this.license = license }

        fun build() = Copyright(this)
    }
}