package jp.co.cyberagent.dojo2019.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object APIClient{
    private const val BASE_URL = "http://api.github.com/"
    private val moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val retrofit = Retrofit
        .Builder()
        .client(OkHttpClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        //.addCallAdapterFactory()
        .build()
        .create(GitHubService::class.java)

}
