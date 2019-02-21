package fi.tomy.salminen.doublehelix.service.persistence.viewmodel

import fi.tomy.salminen.doublehelix.service.persistence.entity.FeedEntity

class FeedViewModel(val entity: FeedEntity) {
    val name = entity.name
    val id = entity.id
}