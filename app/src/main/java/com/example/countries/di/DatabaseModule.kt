package com.example.countries.di

import android.content.Context
import androidx.room.Room
import com.example.countries.data.local.FavoritesDao
import com.example.countries.data.local.LocalDataSource
import com.example.countries.data.local.RoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun localDataSource(favoritesDao: FavoritesDao): LocalDataSource{
        return LocalDataSource(favoritesDao)
    }

    @Provides
    fun provideRoomDB(@ApplicationContext context: Context): RoomDB{
        return Room
            .databaseBuilder(context,RoomDB::class.java,"LocalDB")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideFavoritesDao(roomDB: RoomDB): FavoritesDao{
        return roomDB.favoritesDao()
    }
}