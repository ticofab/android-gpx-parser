package io.ticofab.androidgpxparser.parser.domain

import org.joda.time.DateTime

class Metadata private constructor(builder: Builder) {
    val name = builder.name
    val desc = builder.desc
    val author = builder.author
    val copyright = builder.copyright
    val link = builder.link
    val time = builder.time
    val keywords = builder.keywords
    val bounds = builder.bounds
    val extensions = builder.extensions

    class Builder {
        var name: String? = null
            private set
        var desc: String? = null
            private set
        var author: Author? = null
            private set
        var copyright: Copyright? = null
            private set
        var link: Link? = null
            private set
        var time: DateTime? = null
            private set
        var keywords: String? = null
            private set
        var bounds: Bounds? = null
            private set
        val extensions: String? = null

        fun setName(name: String?) = apply { this.name = name }

        fun setDesc(desc: String?) = apply { this.desc = desc }

        fun setAuthor(author: Author?) = apply { this.author = author }

        fun setCopyright(copyright: Copyright?) = apply { this.copyright = copyright }

        fun setLink(link: Link?) = apply { this.link = link }

        fun setTime(time: DateTime?) = apply { this.time = time }

        fun setKeywords(keywords: String?) = apply { this.keywords = keywords }

        fun setBounds(bounds: Bounds?) = apply { this.bounds = bounds }

        fun build() = Metadata(this)
    }
}