package id.ac.unpas.tokoelektronik.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.tokoelektronik.model.SmartPhone

@Dao
interface SmartphoneDao {
    @Query("SELECT * FROM Smartphone")
    fun loadAll(): LiveData<List<SmartPhone>>

    @Query("SELECT * FROM Smartphone")
    suspend fun getList(): List<SmartPhone>

    @Query("SELECT * FROM Smartphone WHERE id = :id")
    suspend fun find(id: String): SmartPhone?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: SmartPhone)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<SmartPhone>)

    @Delete
    fun delete(item: SmartPhone)

    @Query("DELETE FROM Smartphone WHERE id = :id")
    suspend fun delete(id: String)
}