package com.mukesh.photoapp_flickr.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mukesh.photoapp_flickr.network.NetworkState
import com.mukesh.photoapp_flickr.R
import com.mukesh.photoapp_flickr.adapters.PhotoAdapter
import com.mukesh.photoapp_flickr.model.Photo
import com.mukesh.photoapp_flickr.viewmodels.AbstractListFragmentViewModel

/**
 * Common fragment which can be used for other class also if similar UI
 */
open class AbstractListFragment : Fragment() {
    protected var mViewModel: AbstractListFragmentViewModel<*>? = null
    private var mRecyclerView: RecyclerView? = null
    private var mPhotoAdapter = PhotoAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_images, container, false)
        mRecyclerView = view.findViewById(R.id.fragment_images_recyclerView)

        //Obserview model here
        mViewModel?.photosList?.observe(viewLifecycleOwner, Observer {
                pagedList: PagedList<*> -> mPhotoAdapter.submitList(pagedList as PagedList<Photo?>) })

        //Observe network status
        mViewModel?.networkState?.observe(viewLifecycleOwner, Observer {
                networkState: NetworkState -> mPhotoAdapter.setNetworkState(networkState) })

        //Set recyclerview here
        val layoutmanager = LinearLayoutManager(context)
        layoutmanager.orientation = RecyclerView.VERTICAL
        mRecyclerView?.layoutManager = layoutmanager
        mRecyclerView?.adapter = mPhotoAdapter
        return view
    }

}