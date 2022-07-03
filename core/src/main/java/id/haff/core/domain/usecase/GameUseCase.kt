package id.haff.core.domain.usecase

import id.haff.core.data.Resource
import id.haff.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getAllGames(): Flow<Resource<List<Game>>>
    fun getFavoriteGames(): Flow<List<Game>>
    fun setFavoriteGame(game: Game, isFavorite: Boolean)
    fun getDetailGame(game:Game): Flow<Resource<Game>>
    suspend fun searchGame(string: String): Resource<List<Game>>
}