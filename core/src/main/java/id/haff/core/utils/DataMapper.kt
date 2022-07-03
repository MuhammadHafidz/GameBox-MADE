package id.haff.core.utils

import android.text.Html
import android.util.Log
import id.haff.core.data.local.entity.GameEntity
import id.haff.core.data.remote.response.GameDetailResponse
import id.haff.core.data.remote.response.GameResponse
import id.haff.core.domain.model.Game

object DataMapper {
    fun mapResponsesToEntities(input: List<GameResponse.Result>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            Log.d("CHECK GAME", "mapResponsesToEntities: $it")

            val game = GameEntity(
                id =  it.id.toString(),
                name = it.name,
                image = it.backgroundImage ?: "",
                description = null,
                releaseDate = it.released ?: "",
                isFavorite = false,
                url = null
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapResponsesToDomains(input: List<GameResponse.Result>): List<Game> {
        val gameList = ArrayList<Game>()
        input.map {
            val game = Game(
                id =  it.id.toString(),
                name = it.name,
                image = it.backgroundImage ?: "",
                description = null,
                releaseDate = it.released ?: "",
                isFavorite = false,
                url = null
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                id =  it.id,
                name = it.name,
                image = it.image,
                description = it.description,
                releaseDate = it.releaseDate,
                isFavorite = it.isFavorite,
                url = it.url
            )
        }

    fun mapEntityToDomain(input: GameEntity): Game =
        Game(
            id =  input.id,
            name = input.name,
            image = input.image,
            description = input.description,
            releaseDate = input.releaseDate,
            isFavorite = input.isFavorite,
            url = input.url
        )

    fun mapDomainToEntity(input: Game) = GameEntity(
        id =  input.id,
        name = input.name,
        image = input.image,
        description = input.description,
        releaseDate = input.releaseDate,
        isFavorite = input.isFavorite,
        url = input.url
    )

    fun mapDetailResponseToEntity(input: GameDetailResponse): GameEntity {
        return GameEntity(
            id =  input.id.toString(),
            name = input.name,
            image = input.backgroundImage,
            description = Html.fromHtml(input.description, Html.FROM_HTML_MODE_LEGACY).toString(),
            releaseDate = input.released,
            isFavorite = false,
            url = input.metacriticUrl
        )
    }
}