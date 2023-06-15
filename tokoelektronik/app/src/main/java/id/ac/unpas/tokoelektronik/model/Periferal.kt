package id.ac.unpas.tokoelektronik.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Periferal(
    @PrimaryKey val id: String,
    val nama: String,
    val harga: Int,
    val deskripsi: String,
    val jenis: String,
)
