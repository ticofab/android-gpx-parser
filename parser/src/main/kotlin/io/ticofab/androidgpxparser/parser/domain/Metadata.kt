package io.ticofab.androidgpxparser.parser.domain

import org.joda.time.DateTime

class Metadata private constructor(builder: Builder) {
    val name: String?
    val desc: String?
    val author: Author?
    val copyright: Copyright?
    val link: Link?
    val time: DateTime?
    val keywords: String?
    val bounds: Bounds?
    val extensions: String?

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
        fun setName(name: String?): Builder {
            this.name = name
            return this
        }

        fun setDesc(desc: String?): Builder {
            this.desc = desc
            return this
        }

        fun setAuthor(author: Author?): Builder {
            this.author = author
            return this
        }

        fun setCopyright(copyright: Copyright?): Builder {
            this.copyright = copyright
            return this
        }

        fun setLink(link: Link?): Builder {
            this.link = link
            return this
        }

        fun setTime(time: DateTime?): Builder {
            this.time = time
            return this
        }

        fun setKeywords(keywords: String?): Builder {
            this.keywords = keywords
            return this
        }

        fun setBounds(bounds: Bounds?): Builder {
            this.bounds = bounds
            return this
        }

        fun build(): Metadata {
            return Metadata(this)
        }
    }

    init {
        name = builder.name
        desc = builder.desc
        author = builder.author
        copyright = builder.copyright
        link = builder.link
        time = builder.time
        keywords = builder.keywords
        bounds = builder.bounds
        extensions = builder.extensions
    }
}