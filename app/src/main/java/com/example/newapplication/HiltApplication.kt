package com.example.newapplication

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.newapplication.apiFiles.UserServiceApiResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

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

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSource {
    fun provides(context: Context): SharedPreferences {
        return context.getSharedPreferences("Fake_Instagram", Context.MODE_PRIVATE)
    }
}

@Module
@InstallIn(SingletonComponent::class) // Specifies that this module will be available throughout the application
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    }
}
