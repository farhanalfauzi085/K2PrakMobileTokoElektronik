package com.example.kursusalatmusik.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kursusalatmusik.model.KursusAlatMusik

@Dao
interface KursusAlatMusikDao {
    @Query("SELECT * FROM KursusAlatMusik")
    fun loadAll(): LiveData<List<KursusAlatMusik>>

    @Query("SELECT * FROM KursusAlatMusik WHERE id = :id")
    fun find(id: String): KursusAlatMusik?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items:KursusAlatMusik)

    @Delete
    fun delete(item: KursusAlatMusik)
}