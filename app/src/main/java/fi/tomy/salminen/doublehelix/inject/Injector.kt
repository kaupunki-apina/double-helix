package fi.tomy.salminen.doublehelix.inject


interface Injector<T> {

    fun component(): T

    fun inject()
}