package fi.tomy.salminen.doublehelix.inject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider


class ViewModelFactory<T: ViewModel> @Inject constructor(
    private val viewModel: Provider<T>
) : ViewModelProvider.Factory {

    override fun <U : ViewModel> create(modelClass: Class<U>): U = viewModel.get() as U
}