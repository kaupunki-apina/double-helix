package fi.tomy.salminen.doublehelix.feature.article

import dagger.Subcomponent
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragmentComponent
import fi.tomy.salminen.doublehelix.inject.fragment.FragmentScope


@FragmentScope
@Subcomponent(modules = [ArticleFragmentModule::class])
interface ArticleFragmentComponent : BaseFragmentComponent {

    fun inject(articleFragment: ArticleFragment)
}