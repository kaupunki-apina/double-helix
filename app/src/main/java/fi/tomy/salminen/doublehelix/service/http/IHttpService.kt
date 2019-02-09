package fi.tomy.salminen.doublehelix.service.http

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import io.reactivex.Observable


abstract  class IHttpService {
    val xmlMapper = XmlMapper()

    inline fun <reified T> getXml(url: String ): Observable<T> {
        return makeGetRequest(url).map {
                xmlResponse: String -> xmlMapper.readValue(xmlResponse, T::class.java)
        }
    }

    abstract fun makeGetRequest(url: String): Observable<String>
}