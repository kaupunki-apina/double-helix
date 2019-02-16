package fi.tomy.salminen.doublehelix.feature.feed.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
class RssSubscriptionModel {

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
        }
    }
}
