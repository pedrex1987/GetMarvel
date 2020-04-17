package br.com.pedrex.marvel.infra

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BuildRetrofit {
    fun build() : Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.apply {
            addInterceptor(logging)
            addInterceptor(AuthInterceptor())
            addNetworkInterceptor(StethoInterceptor())
        }


        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
    }
}