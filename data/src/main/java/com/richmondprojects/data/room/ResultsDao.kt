package com.richmondprojects.data.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.richmondprojects.domain.model.Results

@Dao
interface ResultsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(result: List<Results>)

    @Query("SELECT * FROM characters_table")
    fun getAllCharacters(): PagingSource<Int, Results>

    @Query("DELETE FROM characters_table")
    suspend fun deleteAllCharacters()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllKeys(key: List<ResultRemoteKey>)

    @Query("DELETE FROM Remote_key")
    suspend fun deleteAllKeys()

    @Query("SELECT * FROM Remote_key WHERE name = :name")
    suspend fun getRemoteKey(name: String): ResultRemoteKey

}