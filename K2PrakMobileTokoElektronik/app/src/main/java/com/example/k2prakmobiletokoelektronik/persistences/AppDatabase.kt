package com.example.k2prakmobiletokoelektronik.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.k2prakmobiletokoelektronik.model.Komputer
import com.example.k2prakmobiletokoelektronik.model.Periferal
import com.example.k2prakmobiletokoelektronik.model.Smartphone

@Database(entities = [Smartphone::class, Komputer::class, Periferal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    //    komputer
    abstract fun komputerDao(): KomputerDao
    //    periferal
    abstract fun periferalDao(): PeriferalDao
    //    smartphone
    abstract fun smartphoneDao(): SmartphoneDao

}