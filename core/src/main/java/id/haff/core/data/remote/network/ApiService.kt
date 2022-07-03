package id.haff.core.data.remote.network

import id.haff.core.data.remote.response.GameDetailResponse
import id.haff.core.data.remote.response.GameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun getList(
        @Query("key") apiKey: String
    ): GameResponse

    @GET("games/{id}")
    suspend fun getDetail(
        @Path("id") id: String,
        @Query("key") apiKey: String
    ): GameDetailResponse

    @GET("games")
    suspend fun searchGame(
        @Query("search") search: String,
        @Query("key") apiKey: String
    ): GameResponse
}