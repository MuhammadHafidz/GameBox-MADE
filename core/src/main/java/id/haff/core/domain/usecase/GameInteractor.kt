package id.haff.core.domain.usecase

import id.haff.core.data.Resource
import id.haff.core.domain.model.Game
import id.haff.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GameInteractor @Inject constructor(private val gameRepository: IGameRepository): GameUseCase{
    override fun getAllGames(): Flow<Resource<List<Game>>> = gameRepository.getAllGames()

    override fun getFavoriteGames(): Flow<List<Game>> = gameRepository.getFavoriteGames()

    override fun setFavoriteGame(game: Game, isFavorite: Boolean) = gameRepository.setFavoriteGame(game, isFavorite)

    override fun getDetailGame(game: Game): Flow<Resource<Game>> = gameRepository.getDetailGame(game)

    override suspend fun searchGame(string: String): Resource<List<Game>> = gameRepository.searchGame(string).first()
}