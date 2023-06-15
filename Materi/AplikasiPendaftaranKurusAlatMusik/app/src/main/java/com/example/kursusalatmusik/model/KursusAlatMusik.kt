package com.example.kursusalatmusik.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class KursusAlatMusik(
    @PrimaryKey val id: String,
    val nama: String,
    val jeniskelamin: String,
    val nohp: String,
    val selectedalatmusik : String,
    val harikursus : String,
)