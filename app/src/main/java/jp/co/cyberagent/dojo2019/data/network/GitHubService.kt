package jp.co.cyberagent.dojo2019.data.network

import androidx.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService{
    @GET("users/{user}/repos")
    fun fetchRepoList(@Path("user") user: String):LiveData<List<Repository>>
}