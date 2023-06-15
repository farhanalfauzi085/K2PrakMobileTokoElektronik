package id.ac.unpas.tokoelektronik.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SmartPhone (
    @PrimaryKey val id: String,
    val model: String,
    val warna: String,
    val storage: Int,
    val tanggal_rilis: String,
    val sistem_operasi: String
    )

