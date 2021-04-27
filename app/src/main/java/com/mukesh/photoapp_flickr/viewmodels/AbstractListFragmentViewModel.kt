package com.mukesh.photoapp_flickr.viewmodels

import AbstractListDataSourceFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PagedList.Config.Builder
import com.mukesh.photoapp_flickr.network.AppClient
import com.mukesh.photoapp_flickr.network.NetworkState
import java.util.concurrent.Executors

/**
 * Common class to ceate view model
 */
abstract class AbstractListFragmentViewModel<T> : ViewModel() {
    val networkState: LiveData<NetworkState>
    val photosList: LiveData<PagedList<T>>
    abstract fun createDataSourceFactory(): AbstractListDataSourceFactory<T>

    init {
        val config = Builder()
                .setEnablePlaceholders(false)
                .setPageSize(AppClient.PAGE_SIZE)
                .setPrefetchDistance(AppClient.PREFETCH_DISTANCE)
                .setInitialLoadSizeHint(AppClient.PAGE_SIZE)
                .build()
        val dataSourceFactory = createDataSourceFactory()
        networkState = Transformations.switchMap(dataSourceFactory.liveDataSource) { obj -> obj.networkState }
        photosList = LivePagedListBuilder(dataSourceFactory, config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()
    }
}