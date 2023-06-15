package com.example.pencatatanharian.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pencatatanharian.model.PencatatanKeuangan

@Dao
interface PencatatanHarianDao {
    @Query("SELECT * FROM PencatatanKeuangan")
    fun loadAll(): LiveData<List<PencatatanKeuangan>>
    @Query("SELECT * FROM PencatatanKeuangan WHERE id = :id")
    fun find(id: String): PencatatanKeuangan?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: PencatatanKeuangan)
    @Delete
    fun delete(item: PencatatanKeuangan)
}