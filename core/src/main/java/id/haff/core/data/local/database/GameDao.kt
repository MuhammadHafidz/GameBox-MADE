package id.haff.core.data.local.database

import androidx.room.*
import id.haff.core.data.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    fun getAllGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM game where id = :id")
    fun getGames(id: String): Flow<GameEntity>

    @Query("SELECT * FROM game where is_favorite = 1")
    fun getFavoriteGames(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: List<GameEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: GameEntity)

    @Update
    fun updateFavoriteGame(game: GameEntity)
}