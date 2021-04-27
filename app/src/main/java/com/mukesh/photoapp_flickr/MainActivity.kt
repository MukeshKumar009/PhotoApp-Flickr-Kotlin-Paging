package com.mukesh.photoapp_flickr

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProviders
import com.mukesh.photoapp_flickr.ui.SearchListFragment
import com.mukesh.photoapp_flickr.viewmodels.SearchActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main Activity of the application.
 * In our case thid is Search Activity
 */
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SearchActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this)[SearchActivityViewModel::class.java]
        
        //Start search when search or enter button is pressed
        searchText.setOnEditorActionListener { _, _, _ ->
            viewModel.query = searchText!!.text.toString()
            doSearch()
            true
        }
    }

    override fun onResume() {
        super.onResume()
        //search all the images at first time load
        viewModel.query = "all"
        doSearch()
    }
    /**
     * Perform search
     */
    private fun doSearch() {
        val query = viewModel.query
        val tagsExtra = viewModel.tags.joinToString(",")
        //Only search is text is not null
        if ((query != null && query.isNotEmpty()) || tagsExtra.isNotEmpty()) {
            val searchListFragment = SearchListFragment.newInstance(query, tagsExtra, "relevance")
            //Add fragment to activity
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, searchListFragment, "Search items")
                .commit()
            val view = this.currentFocus ?: View(this)

            //Hide soft keyboard
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}