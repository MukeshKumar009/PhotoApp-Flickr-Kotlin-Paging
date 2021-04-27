package com.mukesh.photoapp_flickr.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.mukesh.photoapp_flickr.datasource.SearchListDataSourceFactory
import com.mukesh.photoapp_flickr.model.Photo

/**
 * Factory to create Viewmodels here
 */
class SearchFragmentViewModelFactory(private val query: String, private val tags: String, private val sort: String) : Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchListFragmentViewModel() as T
    }

    inner class SearchListFragmentViewModel : AbstractListFragmentViewModel<Photo?>() {
        override fun createDataSourceFactory(): SearchListDataSourceFactory {
            return SearchListDataSourceFactory(query, tags, sort)
        }
    }

}