package io.ticofab.androidgpxparser.parser.domain

class Copyright private constructor(builder: Builder) {
    val author: String?
    val year: Int?
    val license: String?

    class Builder {
        var author: String? = null
            private set
        var year: Int? = null
            private set
        var license: String? = null
            private set

        fun setAuthor(author: String?): Builder {
            this.author = author
            return this
        }

        fun setYear(year: Int?): Builder {
            this.year = year
            return this
        }

        fun setLicense(license: String?): Builder {
            this.license = license
            return this
        }

        fun build(): Copyright {
            return Copyright(this)
        }
    }

    init {
        author = builder.author
        year = builder.year
        license = builder.license
    }
}