package com.example.giantbombapiproject.di

import com.example.giantbombapiproject.api.GiantBombApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(GiantBombApi.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideGiantBombApi(retrofit: Retrofit): GiantBombApi =
        retrofit.create(GiantBombApi::class.java)
}