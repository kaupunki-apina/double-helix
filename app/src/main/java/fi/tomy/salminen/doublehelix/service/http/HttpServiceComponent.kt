package fi.tomy.salminen.doublehelix.service.http

import dagger.Component
import fi.tomy.salminen.doublehelix.inject.application.BaseApplicationComponent


@ServiceScope
@Component(dependencies = [BaseApplicationComponent::class], modules = [HttpServiceModule::class])
interface HttpServiceComponent {

}