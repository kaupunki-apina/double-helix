package fi.tomy.salminen.doublehelix.inject


interface Injector<T> {

    fun createComponent(): T

    fun inject()
}