package core.network.di

import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import core.datastore.DataStore
import core.network.BearerTokenRefresher
import core.network.AppRemoteApi
import core.network.AppRemoteApiImpl

import core.network.baseHttpClient
import core.network.buildAuthHttpClient

@Suppress("ConstPropertyName")
internal object Named {
    object HttpClient {
        const val base = "base-http-client"
        const val noAuth = "no-auth-http-client"
        const val auth = "auth-http-client"
    }
}

fun networkModule(): Module = module {
    includes(platformNetworkModule())

    single(named(Named.HttpClient.base)) {
        baseHttpClient()
    }
    single(named(Named.HttpClient.auth)) {
        buildAuthHttpClient(get<HttpClient>(named(Named.HttpClient.base)), get<DataStore>())
    }

    single<AppRemoteApi> {
        AppRemoteApiImpl(get<HttpClient>(named(Named.HttpClient.auth)))
    }

    singleOf(::BearerTokenRefresher)
}

internal expect fun platformNetworkModule(): Module



