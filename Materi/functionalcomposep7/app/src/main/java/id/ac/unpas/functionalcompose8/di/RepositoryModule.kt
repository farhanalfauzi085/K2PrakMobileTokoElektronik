package id.ac.unpas.functionalcompose8.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.ac.unpas.functionalcompose8.networks.SetoranSampahApi
import id.ac.unpas.functionalcompose8.persistences.SetoranSampahDao
import id.ac.unpas.functionalcompose8.repositories.SetoranSampahRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideSetoranSampahRepository(
        api: SetoranSampahApi,
        dao: SetoranSampahDao
    ): SetoranSampahRepository {
        return SetoranSampahRepository(api, dao)
    }
}
