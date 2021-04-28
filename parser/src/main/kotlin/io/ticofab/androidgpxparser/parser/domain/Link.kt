package io.ticofab.androidgpxparser.parser.domain

/**
 * Created by Stu Stirling on 04/10/2017.
 */
class Link private constructor(builder: Builder) {
    val href = builder.linkHref
    val text = builder.linkText
    val type = builder.linkType

    class Builder {
        var linkHref: String? = null
            private set
        var linkText: String? = null
            private set
        var linkType: String? = null
            private set

        fun setLinkHref(linkHref: String?) = apply { this.linkHref = linkHref }

        fun setLinkText(linkText: String?) = apply { this.linkText = linkText }

        fun setLinkType(linkType: String?) = apply { this.linkType = linkType }

        fun build() = Link(this)
    }
}