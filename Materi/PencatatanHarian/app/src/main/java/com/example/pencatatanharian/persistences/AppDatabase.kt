package com.example.pencatatanharian.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pencatatanharian.model.PencatatanKeuangan

@Database(entities = [PencatatanKeuangan::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pencatatanHarianDao(): PencatatanHarianDao
}