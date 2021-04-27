package com.mukesh.photoapp_flickr.network

import android.util.Log
import com.mukesh.photoapp_flickr.BuildConfig
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

/**
 * Define API key here and other query parameters
 */
class KeyFormatInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", "ffdc2d7c9127d91d97f8bd23a91b1bbf")
                .addQueryParameter("format", "json")
                .addQueryParameter("nojsoncallback", "1")
                .build()
        Log.d(TAG, url.toString())
        val requestBuilder = original.newBuilder()
                .url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

    companion object {
        private val TAG = KeyFormatInterceptor::class.java.simpleName
    }
}
