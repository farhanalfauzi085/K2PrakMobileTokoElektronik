package com.example.pencatatanharian.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PencatatanKeuangan (
    @PrimaryKey val id: String,
    val tanggal: String,
    val keterangan: String,
    val pemasukan: String,
    val pengeluaran: String,

)

