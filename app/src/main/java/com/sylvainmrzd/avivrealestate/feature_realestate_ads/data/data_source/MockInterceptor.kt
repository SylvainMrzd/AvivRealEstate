package com.sylvainmrzd.avivrealestate.feature_realestate_ads.data.data_source

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor : Interceptor {
    private val mockAdsList = this.javaClass.classLoader?.getResource("listings.json")?.readText() ?: ""

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.toUri().toString()
        val responseString = when {
            uri.endsWith("listings.json") -> mockAdsList
            else -> ""
        }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(
                responseString
                    .toByteArray()
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
            .addHeader("content-type", "application/json")
            .build()
    }
}