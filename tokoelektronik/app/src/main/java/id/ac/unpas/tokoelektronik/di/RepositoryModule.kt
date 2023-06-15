package id.ac.unpas.tokoelektronik.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.ac.unpas.tokoelektronik.networks.KomputerApi
import id.ac.unpas.tokoelektronik.networks.PeriferalApi
import id.ac.unpas.tokoelektronik.networks.SmartphoneApi
import id.ac.unpas.tokoelektronik.persistences.KomputerDao
import id.ac.unpas.tokoelektronik.persistences.PeriferalDao
import id.ac.unpas.tokoelektronik.persistences.SmartphoneDao
import id.ac.unpas.tokoelektronik.repositories.KomputerRepository
import id.ac.unpas.tokoelektronik.repositories.PeriferalRepository
import id.ac.unpas.tokoelektronik.repositories.SmartphoneRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideKomputerRepository(
        api: KomputerApi,
        dao: KomputerDao
    ): KomputerRepository {
        return KomputerRepository(api, dao)
    }
    @Provides
    @ViewModelScoped
    fun providePeriferalRepository(
        api: PeriferalApi,
        dao: PeriferalDao
    ): PeriferalRepository {
        return PeriferalRepository(api, dao)
    }

    @Provides
    @ViewModelScoped
    fun provideSmartphoneRepository(
        api: SmartphoneApi,
        dao: SmartphoneDao
    ): SmartphoneRepository {
        return SmartphoneRepository(api, dao)
    }

}