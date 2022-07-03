package id.haff.core.data.local

import id.haff.core.data.local.database.GameDao
import id.haff.core.data.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameLocalDataSource @Inject constructor(private val gameDao: GameDao){
    fun getAllGames(): Flow<List<GameEntity>> = gameDao.getAllGames()

    fun getGame(id: String): Flow<GameEntity> = gameDao.getGames(id)

    fun getFavoriteGames(): Flow<List<GameEntity>> = gameDao.getFavoriteGames()

    suspend fun insertGames(gameList: List<GameEntity>) = gameDao.insertGame(gameList)

    fun insertGames(game: GameEntity) = gameDao.insertGame(game)

    fun setFavoriteGame(game: GameEntity, isFavorite: Boolean){
        game.isFavorite = isFavorite
        gameDao.updateFavoriteGame(game)
    }

    fun updateGame(gameEntity: GameEntity) = gameDao.updateFavoriteGame(gameEntity)
}