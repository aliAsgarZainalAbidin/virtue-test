package com.example.virtue_test.db.modules

import android.os.Build
import com.example.virtue_test.BuildConfig
import com.example.virtue_test.db.ApiInterface
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
object ApiFactory {
    @Provides
    fun create(): ApiInterface {
        val useCoroutine: Boolean = false
        val timeOut: Long = 60
        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            val spec = ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
                .supportsTlsExtensions(true)
                .tlsVersions(
                    TlsVersion.TLS_1_3,
                    TlsVersion.TLS_1_2,
                    TlsVersion.TLS_1_1,
                    TlsVersion.TLS_1_0
                )
                .cipherSuites(
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,
                    CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_DHE_DSS_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA
                )
                .build()

            httpClient.connectionSpecs(Collections.singletonList(spec))
        }

        httpClient.readTimeout(timeOut, TimeUnit.SECONDS)
        httpClient.connectTimeout(timeOut, TimeUnit.SECONDS)
        httpClient.addInterceptor { chain ->
            val original: Request = chain.request()
            val request: Request = original.newBuilder()
                .header("X-RapidAPI-Key", "c30a60c1fdmsh5ab3ea0dc3f650cp19f93fjsn821bf0b807e1")
                .header("X-RapidAPI-Host", "andruxnet-random-famous-quotes.p.rapidapi.com")
                .method(original.method(), original.body())
                .build()

            chain.proceed(request)
        }

        val client = httpClient.build()

        val retrofit =
            if (useCoroutine)
                Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(client)
                    .build()
            else
                Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(client)
                    .build()

        return retrofit.create(ApiInterface::class.java)
    }
}