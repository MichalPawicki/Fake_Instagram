package com.example.newapplication

import android.app.Application
import com.example.newapplication.apiFiles.UserServiceApiResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Downloading API data from randomuser.me
@HiltAndroidApp
class HiltApplication : Application() {

}

interface UserService {
    @GET("api")
    suspend fun getUser(): UserServiceApiResponse

    @GET("api")
    suspend fun getUsers(@Query("results") amount: Int): UserServiceApiResponse
}


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    fun userService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

}
