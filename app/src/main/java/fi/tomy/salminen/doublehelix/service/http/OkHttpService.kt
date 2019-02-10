package fi.tomy.salminen.doublehelix.service.http

import io.reactivex.Observable
import okhttp3.*


class OkHttpService(val client: OkHttpClient): IHttpService() {

    override fun makeGetRequest(url: String): Observable<String> {
        val request = Request.Builder()
            .url(url)
            .build()

        return Observable.fromCallable { client.newCall(request).execute() }
            .map { response: Response -> response.body()?.string() ?: "" }
    }
}