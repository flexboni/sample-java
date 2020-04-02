package boni.sample.kotlin.library.paging

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * DB 접근 객체
 */
@Dao
interface PagingDao {
    /**
     * Room knows how to return a LivePagedListProvider, from which we can get a LiveData and serve
     * it back to UI via ViewModel.
     */
    @Query("SELECT * FROM Paging ORDER BY name COLLATE NOCASE ASC")
    fun allPagingByName(): DataSource.Factory<Int, Paging>

    @Insert
    fun insert(paging: List<Paging>)

    @Insert
    fun insert(paging: Paging)

    @Delete
    fun delete(paging: Paging)
}
