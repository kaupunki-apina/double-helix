package fi.tomy.salminen.doublehelix.service.http

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


abstract  class IHttpService {
    protected val xmlMapper: XmlMapper by lazy {
        XmlMapper()
    }

    inline fun getXml(url: String ): Observable<String> {
        return makeGetRequest(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        /* .map {
                xmlResponse: String -> xmlMapper.readValue(xmlResponse, T::class.java)
        }
        */
    }

    protected abstract fun makeGetRequest(url: String): Observable<String>
}