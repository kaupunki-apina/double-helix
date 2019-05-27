package fi.tomy.salminen.doublehelix.common

import androidx.browser.customtabs.CustomTabsClient

interface ServiceConnectionCallback {
    fun onServiceConnected(client: CustomTabsClient)
    fun onServiceDisconnected()
}