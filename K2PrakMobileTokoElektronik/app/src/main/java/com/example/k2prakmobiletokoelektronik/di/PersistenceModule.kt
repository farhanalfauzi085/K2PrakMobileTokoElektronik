package com.example.k2prakmobiletokoelektronik.di

import android.app.Application
import androidx.room.Room
import com.example.k2prakmobiletokoelektronik.persistences.AppDatabase
import com.example.k2prakmobiletokoelektronik.persistences.KomputerDao
import com.example.k2prakmobiletokoelektronik.persistences.PeriferalDao
import com.example.k2prakmobiletokoelektronik.persistences.SmartphoneDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(
                application,
                AppDatabase::class.java,
                "toko elektronik"
            )
            .fallbackToDestructiveMigration()
            .build()
    }
    //    komputer
    @Provides
    @Singleton
    fun provideKomputerDao(appDatabase: AppDatabase): KomputerDao {
        return appDatabase.komputerDao()
    }

    //    periferal
    @Provides
    @Singleton
    fun providePeriferalDao(appDatabase: AppDatabase): PeriferalDao {
        return appDatabase.periferalDao()
    }
    //    smartphone
    @Provides
    @Singleton
    fun provideSmartphoneDao(appDatabase: AppDatabase): SmartphoneDao {
        return appDatabase.smartphoneDao()
    }
}