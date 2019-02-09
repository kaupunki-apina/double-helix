package fi.tomy.salminen.doublehelix.service.http

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import io.reactivex.Observable
import okhttp3.*
import java.io.IOException


class OkHttpService(val client: OkHttpClient): IHttpService() {

    override fun makeGetRequest(url: String): Observable<String> {
        val request = Request.Builder()
            .url(url)
            .build()

        return Observable.just(client.newCall(request).execute())
            .map { response: Response -> response.body()?.string() ?: "" }
    }
}