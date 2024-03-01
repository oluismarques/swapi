package com.swapi.starwars.data.network.extension

import okhttp3.Request

internal fun Request.Builder.addContentTypeHeader(): Request.Builder {
    return header(name = "Content-Type", value = "application/json")
}
