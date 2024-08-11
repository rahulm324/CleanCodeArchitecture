package com.rhlm.testprojectlf.di

import com.rhlm.testprojectlf.core.utils.Constants
import com.rhlm.testprojectlf.data.api.PostsApi
import com.rhlm.testprojectlf.data.repository.PostsRepositoryImpl
import com.rhlm.testprojectlf.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModules {

   /* @Provides
    @Singleton
    fun loggingInterceptor() : OkHttpClient? {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(loggingInterceptor)
    }*/

/*    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        // Build the OkHttpClient
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build() // Build the OkHttpClient

        return Retrofit.Builder()  // Add return statement
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)  // Pass the built OkHttpClient here
            .build()
    }*/

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    @Provides
    @Singleton
    fun providePostApi(retrofit: Retrofit) : PostsApi = retrofit.create(PostsApi::class.java)

    @Provides
    @Singleton
    fun providePostRepository(api: PostsApi) : PostRepository = PostsRepositoryImpl(api)
}