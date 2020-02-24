package fi.tomy.salminen.doublehelix.feature.viewmodel

import androidx.lifecycle.ViewModel
import javax.inject.Inject
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider
import javax.inject.Singleton


@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T
}