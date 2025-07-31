package core.network

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import core.models.response.RefreshTokenResponse
import core.network.constants.ApiEndpoints.REFRESH_TOKEN
import core.network.extensions.get

interface AppRemoteApi {

    suspend fun refreshToken(
        token: String,
        block: HttpRequestBuilder.() -> Unit
    ): ApiResult<RefreshTokenResponse>

}

class AppRemoteApiImpl private constructor(
    private val httpClient: HttpClient
) : AppRemoteApi {


    override suspend fun refreshToken(
        token: String,
        block: HttpRequestBuilder.() -> Unit
    ): ApiResult<RefreshTokenResponse> =
        httpClient.get(path = REFRESH_TOKEN, block = {
            header("Authorization", "Bearer $token")
            block(this)
        })


    companion object {
        operator fun invoke(httpClient: HttpClient) = with(httpClient) {
            AppRemoteApiImpl(httpClient)
        }
    }
}
