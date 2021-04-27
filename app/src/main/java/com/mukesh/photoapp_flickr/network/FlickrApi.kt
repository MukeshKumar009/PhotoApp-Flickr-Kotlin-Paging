package com.mukesh.photoapp_flickr.network

import com.mukesh.photoapp_flickr.model.SearchPhotos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API interface for network call
 */
interface FlickrApi {


    @GET("services/rest/?method=flickr.photos.search" +
            "&extras=date_taken,url_o,url_z,original_format,icon_server,owner_name,o_dims,tags" +
            "&tag_mode=all")
    fun searchPhotos(@Query("text") text: String?,
                     @Query("tags") tags: String?,
                     @Query("sort") sort: String?,
                     @Query("page") page: Int,
                     @Query("per_page") per_page: Int): Call<SearchPhotos>

}