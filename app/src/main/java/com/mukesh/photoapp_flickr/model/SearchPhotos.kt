package com.mukesh.photoapp_flickr.model

/**
 * Return object from API call
 */
class SearchPhotos {

    var photos: PhotosBean? = null
    var stat: String? = null

    class PhotosBean {

        var page = 0
        var pages = 0
        var perpage = 0
        var total: String? = null
        var photo: List<Photo>? = null

    }
}
