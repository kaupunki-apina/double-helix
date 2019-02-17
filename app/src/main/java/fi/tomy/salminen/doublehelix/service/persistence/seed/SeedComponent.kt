package fi.tomy.salminen.doublehelix.service.persistence.seed

import dagger.Subcomponent


@Subcomponent(modules = [SeedModule::class])
interface SeedComponent {

    fun inject(databaseSeed: DatabaseSeed)
}