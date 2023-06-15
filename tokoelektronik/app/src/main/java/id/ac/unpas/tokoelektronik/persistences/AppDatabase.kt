package id.ac.unpas.tokoelektronik.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.tokoelektronik.model.Komputer
import id.ac.unpas.tokoelektronik.model.Periferal
import id.ac.unpas.tokoelektronik.model.SmartPhone

@Database(entities = [Komputer::class, Periferal::class, SmartPhone::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun KomputerDao(): KomputerDao

    abstract fun PeriferalDao(): PeriferalDao

    abstract fun SmartphoneDao(): SmartphoneDao
}