package com.example.kanyerestquotes.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kanyerestquotes.data.model.KanyeData

@Dao
interface QuoteDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quote: KanyeData)

    @Query("SELECT*FROM KanyeData")
    fun getAll(): LiveData<List<KanyeData>>

    @Delete
    suspend fun delete(quote:KanyeData)

    @Query("DELETE FROM KanyeData")
    suspend fun deleteAll()

    @Update
    suspend fun update(quote:KanyeData)

    @Query ("SELECT * FROM KanyeData WHERE quote LIKE '%' || :name || '%'")
    suspend fun getAllFavByName(name:String): List<KanyeData>


}