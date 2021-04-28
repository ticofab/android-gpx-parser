package io.ticofab.androidgpxparser.parser.domain

class Author private constructor(builder: Builder) {
    val name: String?
    val email: Email?
    val link: Link?

    class Builder {
        var name: String? = null
            private set
        var email: Email? = null
            private set
        var link: Link? = null
            private set

        fun setName(name: String?): Builder {
            this.name = name
            return this
        }

        fun setEmail(email: Email?): Builder {
            this.email = email
            return this
        }

        fun setLink(link: Link?): Builder {
            this.link = link
            return this
        }

        fun build(): Author {
            return Author(this)
        }
    }

    init {
        name = builder.name
        email = builder.email
        link = builder.link
    }
}