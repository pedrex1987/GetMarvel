package br.com.pedrex.marvel.infra

import br.com.pedrex.marvel.BuildConfig
import br.com.pedrex.marvel.extension.toMd5
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor : Interceptor {

    companion object {
        private const val TIMESTAMP_KEY = "ts"
        private const val HASH_KEY = "hash"
        private const val APIKEY_KEY = "apikey"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val timestamp = System.currentTimeMillis().toString()
        val publicKey = BuildConfig.API_KEY
        val hash: String = generateHash(timestamp, publicKey, BuildConfig.API_HASH)
        var request: Request = chain.request()
        val url: HttpUrl = request.url()
            .newBuilder()
            .addQueryParameter(TIMESTAMP_KEY, timestamp)
            .addQueryParameter(APIKEY_KEY, publicKey)
            .addQueryParameter(HASH_KEY, hash)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }


    private fun generateHash(
        timestamp: String,
        publicKey: String,
        privateKey: String
    ): String {
            val value = timestamp + privateKey + publicKey
            return value.toMd5()
    }
}
