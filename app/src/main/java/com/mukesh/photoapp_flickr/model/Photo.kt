package com.mukesh.photoapp_flickr.model

import android.os.Parcelable
import android.util.Log
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import kotlinx.android.parcel.Parcelize

/**
 * Model class for Photo item
 */
@Parcelize
data class Photo(
        var id: String?,
        var owner: String?,
        var secret: String?,
        var server: String?,
        var farm: Int?,
        var title: String?,
        var ispublic: Int?,
        var isfriend: Int?,
        var isfamily: Int?,
        var datetaken: String?,
        var iconserver: String?,
        var iconfarm: Int?,
        var originalsecret: String?,
        var originalformat: String?,
        var url_o: String?,
        var height_o: Int?,
        var width_o: Int?,
        var height_z: Int = 0,
        var width_z: Int = 0,
        var owner_name: String?,
        var tags: String?,
        var primarykey: Int,
        var time: Long
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        return if (other is Photo) {
            other.id == id
        } else false
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    val iconUrl: String
        get() = if (Integer.parseInt(iconserver ?: "-1") > 0) {
            ("https://farm" + iconfarm.toString() + ".staticflickr.com/"
                    + iconserver + "/buddyicons/"
                    + owner + ".jpg")
        } else {
            "https://www.flickr.com/images/buddyicon.jpg"
        }


    val url_z: String
        get() = ("https://farm" + farm.toString() + ".staticflickr.com/" + server
                + "/" + id + "_" + secret + ".jpg")

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        var DIFF_CALLBACK: ItemCallback<Photo?> = object : ItemCallback<Photo?>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id === newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem == newItem
            }
        }
    }
}
