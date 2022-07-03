package id.haff.core.data

import id.haff.core.data.local.GameLocalDataSource
import id.haff.core.data.remote.GameRemoteDataSource
import id.haff.core.data.remote.network.ApiResponse
import id.haff.core.data.remote.response.GameDetailResponse
import id.haff.core.data.remote.response.GameResponse
import id.haff.core.domain.model.Game
import id.haff.core.domain.repository.IGameRepository
import id.haff.core.utils.AppExecutors
import id.haff.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(
    private val remoteDataSource: GameRemoteDataSource,
    private val localDataSource: GameLocalDataSource,
    private val appExecutors: AppExecutors
): IGameRepository{
    override fun getAllGames(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse.Result>>(){
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGames().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse.Result>>> =
                remoteDataSource.getAllGames()

            override suspend fun saveCallResult(data: List<GameResponse.Result>) {
                val gameList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGames(gameList)
            }
        }.asFlow()

    override fun getFavoriteGames(): Flow<List<Game>> {
        return localDataSource.getFavoriteGames().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGame(game: Game, isFavorite: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGame(gameEntity, isFavorite) }
    }

    override fun getDetailGame(game: Game): Flow<Resource<Game>> =
        object : NetworkBoundResource<Game, GameDetailResponse>() {
            override fun loadFromDB(): Flow<Game> {
                return localDataSource.getGame(game.id).map {
                    DataMapper.mapEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Game?): Boolean
            = data?.description == null

            override suspend fun createCall(): Flow<ApiResponse<GameDetailResponse>> {
                return remoteDataSource.getDetailGames(game)
            }

            override suspend fun saveCallResult(data: GameDetailResponse) {
                appExecutors.diskIO().execute { localDataSource.updateGame(DataMapper.mapDetailResponseToEntity(data)) }
            }
        }.asFlow()

    override suspend fun searchGame(string: String): Flow<Resource<List<Game>>> {
        return flow {
            when (val apiResponse = remoteDataSource.searchGame(string).first()) {
                is ApiResponse.Success -> {
                    localDataSource.insertGames(DataMapper.mapResponsesToEntities(apiResponse.data))
                    emit(Resource.Success(DataMapper.mapResponsesToDomains(apiResponse.data)))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Success(listOf()))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.error))
                }
            }
        }
    }

}