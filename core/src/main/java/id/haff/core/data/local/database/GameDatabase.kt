package id.haff.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import id.haff.core.data.local.entity.GameEntity

@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}