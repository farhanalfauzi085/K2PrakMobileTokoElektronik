package com.example.kursusalatmusik.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kursusalatmusik.model.KursusAlatMusik

@Database(entities = [KursusAlatMusik::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
     abstract fun kursusAlatMusikDao(): KursusAlatMusikDao
}

