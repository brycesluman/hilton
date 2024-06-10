package org.sluman.hilton.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sluman.hilton.data.IpGeoRepositoryImpl
import org.sluman.hilton.database.GeoDao
import org.sluman.hilton.database.GeoDatabase
import org.sluman.hilton.domain.IpGeoRepository
import org.sluman.hilton.data.network.ApiClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GeoDatabase {
        return Room.databaseBuilder(
            context,
            GeoDatabase::class.java,
            GeoDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideGeoDAO(geoDatabase: GeoDatabase): GeoDao {
        return geoDatabase.geoDao()
    }

    @Provides
    @Singleton
    fun provideApiClient(): ApiClient {
        return ApiClient()
    }

    @Provides
    @Singleton
    fun provideIpGeoRepository(apiClient: ApiClient, geoDao: GeoDao): IpGeoRepository {
        return IpGeoRepositoryImpl(apiClient, geoDao)
    }
}
