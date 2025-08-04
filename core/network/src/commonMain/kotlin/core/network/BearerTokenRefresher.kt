package core.network

import com.github.kittinunf.result.getOrNull
import com.github.kittinunf.result.map
import com.github.kittinunf.result.onFailure
import com.github.kittinunf.result.onSuccess
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.request.HttpRequestBuilder
import core.datastore.DataStore
import core.network.extensions.saveBearerTokens
import core.analytics.Log

internal class BearerTokenRefresher(
    private val dataStore: DataStore,
    private val remoteApi: AppRemoteApi,
) {
    suspend fun refreshToken(block: HttpRequestBuilder.() -> Unit): BearerTokens? {
        val refreshToken = dataStore.refreshToken ?: return null

        Log.info("Refreshing tokens")
        return remoteApi.refreshToken(refreshToken, block)
            .map {
                it.data?.token?.let { token ->
                    BearerTokens(
                        token.accessToken,
                        token.refreshToken
                    )
                }
            }
            .onSuccess {
                Log.info("Tokens rotated!!, saving them to store")
                dataStore.saveBearerTokens(it)
            }
            .onFailure {
                Log.error(it, "Unable to rotate token.")
                dataStore.isLoggedIn = false
            }.getOrNull()
    }
}
