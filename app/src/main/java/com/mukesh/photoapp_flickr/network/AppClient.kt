package com.mukesh.photoapp_flickr.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * API client builder, we will use retrofit for this.
 */
class AppClient {

    companion object {
        private const val API_URL = "https://api.flickr.com/"
        const val PAGE_SIZE = 5 //Item per page
        const val PREFETCH_DISTANCE = 5 //Loading view after end of items
        const val PACKAGE_NAME = "com.mukesh.photoapp_flickr"

        val flickrApi: FlickrApi by lazy {
            val builder = GsonBuilder()
            builder.registerTypeAdapter(Boolean::class.java, BooleanTypeAdapter())
            val gson = builder.create()
            Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(FlickrApi::class.java)
        }

        private val client by lazy {
            OkHttpClient.Builder()
                .addInterceptor(KeyFormatInterceptor())
                .build()
        }

    }
}