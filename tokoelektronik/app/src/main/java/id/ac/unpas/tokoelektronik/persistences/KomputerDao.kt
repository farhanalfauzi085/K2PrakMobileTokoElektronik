package id.ac.unpas.tokoelektronik.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.tokoelektronik.model.Komputer

@Dao
interface KomputerDao {
    @Query("SELECT * FROM Komputer")
    fun loadAll(): LiveData<List<Komputer>>

    @Query("SELECT * FROM Komputer")
    suspend fun getList(): List<Komputer>

    @Query("SELECT * FROM Komputer WHERE id = :id")
    suspend fun find(id: String): Komputer?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Komputer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Komputer>)

    @Delete
    fun delete(item: Komputer)

    @Query("DELETE FROM Komputer WHERE id = :id")
    suspend fun delete(id: String)
}