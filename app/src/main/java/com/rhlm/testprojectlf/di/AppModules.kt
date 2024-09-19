package com.rhlm.testprojectlf.di

import com.rhlm.testprojectlf.core.utils.Constants
import com.rhlm.testprojectlf.data.api.PostsApi
import com.rhlm.testprojectlf.data.repository.PostsRepositoryImpl
import com.rhlm.testprojectlf.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModules {

    @Provides
    @Singleton
    fun provideOkHttpClient(): okhttp3.OkHttpClient {
        return okhttp3.OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()

                // Add headers to the original request
                val newRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer YOUR_ACCESS_TOKEN")
                    .header("Custom-Header", "HeaderValue")
                    .build()

                // Proceed with the new request
                chain.proceed(newRequest)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(/*okHttpClient: okhttp3.OkHttpClient*/): Retrofit = Retrofit.Builder()
        //.client(okHttpClient)
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