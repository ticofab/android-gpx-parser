package io.ticofab.androidgpxparser.parser.domain

/**
 * Created by Stu Stirling on 04/10/2017.
 */
class Link private constructor(builder: Builder) {
    val href: String?
    val text: String?
    val type: String?

    class Builder {
        var linkHref: String? = null
            private set
        var linkText: String? = null
            private set
        var linkType: String? = null
            private set

        fun setLinkHref(linkHref: String?): Builder {
            this.linkHref = linkHref
            return this
        }

        fun setLinkText(linkText: String?): Builder {
            this.linkText = linkText
            return this
        }

        fun setLinkType(linkType: String?): Builder {
            this.linkType = linkType
            return this
        }

        fun build(): Link {
            return Link(this)
        }
    }

    init {
        href = builder.linkHref
        text = builder.linkText
        type = builder.linkType
    }
}