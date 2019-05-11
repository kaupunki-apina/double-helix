package fi.tomy.salminen.doublehelix.feature.article

import dagger.Subcomponent
import fi.tomy.salminen.doublehelix.inject.activity.ActivityScope
import fi.tomy.salminen.doublehelix.inject.activity.BaseActivityComponent
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragmentModule


@ActivityScope
@Subcomponent(modules = [ArticleActivityModule::class])
interface ArticleActivityComponent : BaseActivityComponent {
    fun inject(articleActivity: ArticleActivity)

    fun plus(
        articleFragmentModule: ArticleFragmentModule,
        baseFragmentModule: BaseFragmentModule
    ): ArticleFragmentComponent
}