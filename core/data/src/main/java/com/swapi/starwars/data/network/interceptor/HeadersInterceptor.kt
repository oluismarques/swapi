package com.swapi.starwars.data.network.interceptor

import com.swapi.starwars.data.network.extension.addContentTypeHeader
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class HeadersInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {

        proceed(
            request().newBuilder()
                .addContentTypeHeader()
                .build()
        )
    }
}
