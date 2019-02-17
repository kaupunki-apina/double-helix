package fi.tomy.salminen.doublehelix.service.rss

import org.simpleframework.xml.*

@Root(name = "rss", strict = false)
class RssModel {

    @field:Element
    var channel: RssChannel? = null

    @Root(name = "channel", strict = false)
    class RssChannel {

        @field:Element
        var title: String? = null

        @field:Element(required = false)
        var description: String? = null

        @field:ElementList(name = "item", required = false, inline = true)
        var items: List<RssItem>? = null

        @Root(name ="item", strict = false)
        class RssItem {

            @field:Element(required = false)
            var title: String? = null

            @field:Element(required = false)
            var link: String? = null

            @field:Element(required = false)
            var description: String? = null

            @field:Element(name = "pubDate", required = false)
            var publishDate: String? = null

            @Path("enclosure")
            @field:Attribute(name = "url", required = false)
            var image: String? = null
        }
    }
}
