

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource.Factory
import com.mukesh.photoapp_flickr.datasource.AbstractListDataSource

/**
 * Call for common uses for data source
 */
abstract class AbstractListDataSourceFactory<T> : Factory<Int?, T>() {
    var liveDataSource = MutableLiveData<AbstractListDataSource<T>>()
}
