package io.ticofab.androidgpxparser.parser.domain

class Email private constructor(builder: Builder) {
    val id: String?
    val domain: String?

    class Builder {
        var id: String? = null
            private set
        var domain: String? = null
            private set

        fun setId(id: String?): Builder {
            this.id = id
            return this
        }

        fun setDomain(domain: String?): Builder {
            this.domain = domain
            return this
        }

        fun build(): Email {
            return Email(this)
        }
    }

    init {
        id = builder.id
        domain = builder.domain
    }
}