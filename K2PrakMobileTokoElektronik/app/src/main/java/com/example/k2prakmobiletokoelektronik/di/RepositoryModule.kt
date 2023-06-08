package com.example.k2prakmobiletokoelektronik.di

import com.example.k2prakmobiletokoelektronik.network.KomputerApi
import com.example.k2prakmobiletokoelektronik.network.PeriferalApi
import com.example.k2prakmobiletokoelektronik.network.SmartphoneApi
import com.example.k2prakmobiletokoelektronik.persistences.KomputerDao
import com.example.k2prakmobiletokoelektronik.persistences.PeriferalDao
import com.example.k2prakmobiletokoelektronik.persistences.SmartphoneDao
import com.example.k2prakmobiletokoelektronik.repositories.KomputerRepository
import com.example.k2prakmobiletokoelektronik.repositories.PeriferalRepository
import com.example.k2prakmobiletokoelektronik.repositories.SmartphoneRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    //    komputer
    @Provides
    @ViewModelScoped
    fun provideKomputerRepository(
        api: KomputerApi,
        dao: KomputerDao
    ): KomputerRepository {
        return KomputerRepository(api, dao)
    }

    //    periferal
    @Provides
    @ViewModelScoped
    fun providePeriferalRepository(
        api: PeriferalApi,
        dao: PeriferalDao
    ): PeriferalRepository {
        return PeriferalRepository(api, dao)
    }

    //    smartphone
    @Provides
    @ViewModelScoped
    fun provideSmartphoneRepository(
        api: SmartphoneApi,
        dao: SmartphoneDao
    ): SmartphoneRepository {
        return SmartphoneRepository(api, dao)
    }
}