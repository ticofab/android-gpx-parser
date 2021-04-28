package io.ticofab.androidgpxparser.parser.domain

class Author private constructor(builder: Builder) {
    val name = builder.name
    val email = builder.email
    val link = builder.link

    class Builder {
        var name: String? = null
            private set
        var email: Email? = null
            private set
        var link: Link? = null
            private set

        fun setName(name: String?) = apply { this.name = name }

        fun setEmail(email: Email?) = apply { this.email = email }

        fun setLink(link: Link?) = apply { this.link = link }

        fun build() = Author(this)
    }
}