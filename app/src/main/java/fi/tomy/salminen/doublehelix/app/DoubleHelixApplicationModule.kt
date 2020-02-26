package fi.tomy.salminen.doublehelix.app

import dagger.Module
import fi.tomy.salminen.doublehelix.inject.application.BaseApplicationModule


@Module(includes = [BaseApplicationModule::class])
abstract class DoubleHelixApplicationModule