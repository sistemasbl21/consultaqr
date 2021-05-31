package com.example.basedatos2.di



import com.example.basedatos2.BuildConfig.DEBUG
import com.example.basedatos2.network.ApiClient
import com.example.basedatos2.utils.ConnectionUtils
import com.example.basedatos2.utils.ConnectionUtilsImpl
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val apiModule = module {

    single<ConnectionUtils> { ConnectionUtilsImpl(androidContext()) }

    fun provideMMarketApi(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }

    single { provideMMarketApi( get() ) }
}

val retrofitModule = module {

    val connectTimeOut : Long = 40
    val readTimeOut : Long = 40

    fun provideHttpClient(): OkHttpClient {
        var okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeOut, TimeUnit.SECONDS)
            .readTimeout(readTimeOut, TimeUnit.SECONDS)
        if (DEBUG){
            val httpLoginInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoginInterceptor)
        }
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(factory))
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    single { provideHttpClient() }
    single { provideGson() }
    single {
        val baseUrl = "https://"
        provideRetrofit(get(), get(), baseUrl)
    }
}