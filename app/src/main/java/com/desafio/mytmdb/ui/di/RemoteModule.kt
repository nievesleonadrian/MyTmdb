package com.desafio.mytmdb.ui.di

import com.desafio.mytmdb.data.remote.API_KEY
import com.desafio.mytmdb.data.remote.BASE_URL
import com.desafio.mytmdb.data.remote.MoviesRemoteImpl
import com.desafio.mytmdb.data.remote.MoviesRestApi
import com.desafio.mytmdb.data.source.MoviesRemote
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
abstract class RemoteModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideGsonConverterFactory(): GsonConverterFactory {
            return GsonConverterFactory.create()
        }

        @Provides
        @JvmStatic
        fun provideRxJavaCallAdapter(): RxJava2CallAdapterFactory {
            return RxJava2CallAdapterFactory.create()
        }

        @Provides
        @JvmStatic
        fun provideInterceptor(): Interceptor = Interceptor { chain ->
            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        @Provides
        @JvmStatic
        fun provideokHttpClient(interceptor: Interceptor): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        @Provides
        @JvmStatic
        fun provideRetrofit(
            okHttpClient: OkHttpClient,
            callAdapterFactory: RxJava2CallAdapterFactory,
            gsonConverterFactory: GsonConverterFactory,
        ) = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()


        @Provides
        @JvmStatic
        fun provideMoviesRestApi(retrofit: Retrofit): MoviesRestApi =
            retrofit.create(MoviesRestApi::class.java)

        @Provides
        @JvmStatic
        fun provideMoviesRemoteDataSource(restApi: MoviesRestApi): MoviesRemote =
            MoviesRemoteImpl(restApi)
    }
}