package jp.co.cyberagent.dojo2019.data.network

import androidx.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService{
    @GET("users/{user}/")
    suspend fun fetchRepoList(@Path("user") user: String):Repository
}