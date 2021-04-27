package com.mukesh.photoapp_flickr.viewmodels

import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.HashSet

/**
 * View model for search activity
 */
class SearchActivityViewModel : ViewModel() {
    val tags: HashSet<String> = HashSet()
    var query: String? = null
}