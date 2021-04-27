package com.mukesh.photoapp_flickr.datasource

import AbstractListDataSourceFactory
import com.mukesh.photoapp_flickr.model.Photo
import com.mukesh.photoapp_flickr.model.SearchPhotos
import com.mukesh.photoapp_flickr.network.AppClient
import com.mukesh.photoapp_flickr.network.AppClient.Companion.flickrApi
import com.mukesh.photoapp_flickr.network.NetworkState
import retrofit2.Response
import java.io.IOException
import kotlin.jvm.Throws

/**
 * Data factory for Image data source
 */

class SearchListDataSourceFactory(private val query: String, private val tags: String, private val sort: String) : AbstractListDataSourceFactory<Photo?>() {
    override fun create(): AbstractListDataSource<Photo?> {
        val dataSource: AbstractListDataSource<Photo?> = object : AbstractListDataSource<Photo?>() {
            @Throws(IOException::class)
            override fun loadFirst(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Photo?>) {
                //Make first network call here
                val response: Response<SearchPhotos?> = flickrApi.searchPhotos(query, tags, sort, 1, AppClient.PAGE_SIZE).execute()
                if (response.isSuccessful && response.code() == 200 && response.body()?.stat == "ok") {
                    callback.onResult(response.body()!!.photos!!.photo!!, null, 2)
                    networkState.postValue(NetworkState.SUCCESS)
                } else {
                    networkState.postValue(NetworkState.FAILED)
                }
            }

            @Throws(IOException::class)
            override fun loadNext(params: LoadParams<Int>, callback: LoadCallback<Int, Photo?>) {
                //Load next data of the page
                val response: Response<SearchPhotos?> = flickrApi.searchPhotos(query, tags, sort, params.key, AppClient.PAGE_SIZE).execute()
                if (response.isSuccessful && response.code() == 200 && response.body()?.stat == "ok") {
                    callback.onResult(response.body()!!.photos!!.photo!!, params.key + 1)
                    networkState.postValue(NetworkState.SUCCESS)
                } else {
                    networkState.postValue(NetworkState.FAILED)
                }
            }
        }
        liveDataSource.postValue(dataSource)
        return dataSource
    }

}
