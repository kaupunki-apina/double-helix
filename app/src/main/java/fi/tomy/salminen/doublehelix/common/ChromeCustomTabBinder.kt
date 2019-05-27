package fi.tomy.salminen.doublehelix.common

import android.content.ComponentName
import android.content.ServiceConnection
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.browser.customtabs.CustomTabsClient
import fi.tomy.salminen.doublehelix.core.BaseActivity
import java.lang.ref.WeakReference


class ChromeCustomTabBinder : ServiceConnectionCallback {
    private val packageName = "com.android.chrome"
    private var client: CustomTabsClient? = null
    private var connection: CustomTabsServiceConnection? = null

    fun bind(activity: BaseActivity<*>) : Boolean {
        if (client != null) return false
        connection = MyServiceConnection(this)
        CustomTabsClient.bindCustomTabsService(activity, packageName, connection)
        return true
    }

    fun unbind(activity: BaseActivity<*>) {
        if (connection == null) return
        activity.unbindService(connection as ServiceConnection)
        client = null
        connection = null
    }

    override fun onServiceConnected(client: CustomTabsClient) {
        this.client = client
        client.warmup(0L)
    }

    override fun onServiceDisconnected() {
        client = null
    }


    inner class MyServiceConnection(connectionCallback: ServiceConnectionCallback) : CustomTabsServiceConnection() {
        private val connectionCallback: WeakReference<ServiceConnectionCallback> = WeakReference(connectionCallback)

        override fun onCustomTabsServiceConnected(name: ComponentName, client: CustomTabsClient) {
            val connectionCallback = connectionCallback.get()
            connectionCallback?.onServiceConnected(client)
        }

        override fun onServiceDisconnected(name: ComponentName) {
            val connectionCallback = connectionCallback.get()
            connectionCallback?.onServiceDisconnected()
        }
    }
}
