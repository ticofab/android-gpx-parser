package io.ticofab.androidgpxparser.parser.domain

class Email private constructor(builder: Builder) {
    val id = builder.id
    val domain = builder.domain

    class Builder {
        var id: String? = null
            private set
        var domain: String? = null
            private set

        fun setId(id: String?) = apply { this.id = id }

        fun setDomain(domain: String?) = apply { this.domain = domain }

        fun build() = Email(this)
    }
}