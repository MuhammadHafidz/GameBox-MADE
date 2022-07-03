package id.haff.core.data.remote

import id.haff.core.BuildConfig
import id.haff.core.data.remote.network.ApiResponse
import id.haff.core.data.remote.network.ApiService
import id.haff.core.data.remote.response.GameDetailResponse
import id.haff.core.data.remote.response.GameResponse
import id.haff.core.domain.model.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllGames(): Flow<ApiResponse<List<GameResponse.Result>>> {
        return flow {
            try {
                val response = apiService.getList(BuildConfig.API_KEY)
                val data = response.results
                if (data.isNotEmpty()){
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailGames(game: Game): Flow<ApiResponse<GameDetailResponse>> {
        return flow {
            try {
                val response = apiService.getDetail(game.id, BuildConfig.API_KEY)
                emit(ApiResponse.Success(response))
            }catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchGame(search: String): Flow<ApiResponse<List<GameResponse.Result>>> {
        return flow {
            try {
                val response = apiService.searchGame(search, BuildConfig.API_KEY)
                val data = response.results
                if (data.isNotEmpty()){
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}