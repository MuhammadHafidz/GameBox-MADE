package id.haff.core.data.remote.response


import com.google.gson.annotations.SerializedName

data class GameDetailResponse(
    @SerializedName("achievements_count")
    var achievementsCount: Int,
    @SerializedName("added")
    var added: Int,
    @SerializedName("added_by_status")
    var addedByStatus: AddedByStatus,
    @SerializedName("additions_count")
    var additionsCount: Int,
    @SerializedName("alternative_names")
    var alternativeNames: List<String>,
    @SerializedName("background_image")
    var backgroundImage: String,
    @SerializedName("background_image_additional")
    var backgroundImageAdditional: String,
    @SerializedName("clip")
    var clip: Any,
    @SerializedName("creators_count")
    var creatorsCount: Int,
    @SerializedName("description")
    var description: String,
    @SerializedName("description_raw")
    var descriptionRaw: String,
    @SerializedName("developers")
    var developers: List<Developer>,
    @SerializedName("dominant_color")
    var dominantColor: String,
    @SerializedName("esrb_rating")
    var esrbRating: EsrbRating,
    @SerializedName("game_series_count")
    var gameSeriesCount: Int,
    @SerializedName("genres")
    var genres: List<Genre>,
    @SerializedName("id")
    var id: Int,
    @SerializedName("metacritic")
    var metacritic: Int,
    @SerializedName("metacritic_platforms")
    var metacriticPlatforms: List<MetacriticPlatform>,
    @SerializedName("metacritic_url")
    var metacriticUrl: String,
    @SerializedName("movies_count")
    var moviesCount: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("name_original")
    var nameOriginal: String,
    @SerializedName("parent_achievements_count")
    var parentAchievementsCount: Int,
    @SerializedName("parent_platforms")
    var parentPlatforms: List<ParentPlatform>,
    @SerializedName("parents_count")
    var parentsCount: Int,
    @SerializedName("platforms")
    var platforms: List<Platform>,
    @SerializedName("playtime")
    var playtime: Int,
    @SerializedName("publishers")
    var publishers: List<Publisher>,
    @SerializedName("rating")
    var rating: Double,
    @SerializedName("rating_top")
    var ratingTop: Int,
    @SerializedName("ratings")
    var ratings: List<Rating>,
    @SerializedName("ratings_count")
    var ratingsCount: Int,
    @SerializedName("reactions")
    var reactions: Reactions,
    @SerializedName("reddit_count")
    var redditCount: Int,
    @SerializedName("reddit_description")
    var redditDescription: String,
    @SerializedName("reddit_logo")
    var redditLogo: String,
    @SerializedName("reddit_name")
    var redditName: String,
    @SerializedName("reddit_url")
    var redditUrl: String,
    @SerializedName("released")
    var released: String,
    @SerializedName("reviews_count")
    var reviewsCount: Int,
    @SerializedName("reviews_text_count")
    var reviewsTextCount: Int,
    @SerializedName("saturated_color")
    var saturatedColor: String,
    @SerializedName("screenshots_count")
    var screenshotsCount: Int,
    @SerializedName("slug")
    var slug: String,
    @SerializedName("stores")
    var stores: List<Store>,
    @SerializedName("suggestions_count")
    var suggestionsCount: Int,
    @SerializedName("tags")
    var tags: List<Tag>,
    @SerializedName("tba")
    var tba: Boolean,
    @SerializedName("twitch_count")
    var twitchCount: Int,
    @SerializedName("updated")
    var updated: String,
    @SerializedName("user_game")
    var userGame: Any,
    @SerializedName("website")
    var website: String,
    @SerializedName("youtube_count")
    var youtubeCount: Int
) {
    data class AddedByStatus(
        @SerializedName("beaten")
        var beaten: Int,
        @SerializedName("dropped")
        var dropped: Int,
        @SerializedName("owned")
        var owned: Int,
        @SerializedName("playing")
        var playing: Int,
        @SerializedName("toplay")
        var toplay: Int,
        @SerializedName("yet")
        var yet: Int
    )

    data class Developer(
        @SerializedName("games_count")
        var gamesCount: Int,
        @SerializedName("id")
        var id: Int,
        @SerializedName("image_background")
        var imageBackground: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("slug")
        var slug: String
    )

    data class EsrbRating(
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var name: String,
        @SerializedName("slug")
        var slug: String
    )

    data class Genre(
        @SerializedName("games_count")
        var gamesCount: Int,
        @SerializedName("id")
        var id: Int,
        @SerializedName("image_background")
        var imageBackground: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("slug")
        var slug: String
    )

    data class MetacriticPlatform(
        @SerializedName("metascore")
        var metascore: Int,
        @SerializedName("platform")
        var platform: Platform,
        @SerializedName("url")
        var url: String
    ) {
        data class Platform(
            @SerializedName("name")
            var name: String,
            @SerializedName("platform")
            var platform: Int,
            @SerializedName("slug")
            var slug: String
        )
    }

    data class ParentPlatform(
        @SerializedName("platform")
        var platform: Platform
    ) {
        data class Platform(
            @SerializedName("id")
            var id: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("slug")
            var slug: String
        )
    }

    data class Platform(
        @SerializedName("platform")
        var platform: Platform,
        @SerializedName("released_at")
        var releasedAt: String,
        @SerializedName("requirements")
        var requirements: Requirements
    ) {
        data class Platform(
            @SerializedName("games_count")
            var gamesCount: Int,
            @SerializedName("id")
            var id: Int,
            @SerializedName("image")
            var image: Any,
            @SerializedName("image_background")
            var imageBackground: String,
            @SerializedName("name")
            var name: String,
            @SerializedName("slug")
            var slug: String,
            @SerializedName("year_end")
            var yearEnd: Any,
            @SerializedName("year_start")
            var yearStart: Int
        )

        data class Requirements(
            @SerializedName("minimum")
            var minimum: String,
            @SerializedName("recommended")
            var recommended: String
        )
    }

    data class Publisher(
        @SerializedName("games_count")
        var gamesCount: Int,
        @SerializedName("id")
        var id: Int,
        @SerializedName("image_background")
        var imageBackground: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("slug")
        var slug: String
    )

    data class Rating(
        @SerializedName("count")
        var count: Int,
        @SerializedName("id")
        var id: Int,
        @SerializedName("percent")
        var percent: Double,
        @SerializedName("title")
        var title: String
    )

    data class Reactions(
        @SerializedName("1")
        var x1: Int,
        @SerializedName("10")
        var x10: Int,
        @SerializedName("11")
        var x11: Int,
        @SerializedName("12")
        var x12: Int,
        @SerializedName("14")
        var x14: Int,
        @SerializedName("15")
        var x15: Int,
        @SerializedName("16")
        var x16: Int,
        @SerializedName("18")
        var x18: Int,
        @SerializedName("2")
        var x2: Int,
        @SerializedName("20")
        var x20: Int,
        @SerializedName("21")
        var x21: Int,
        @SerializedName("3")
        var x3: Int,
        @SerializedName("4")
        var x4: Int,
        @SerializedName("5")
        var x5: Int,
        @SerializedName("6")
        var x6: Int,
        @SerializedName("7")
        var x7: Int,
        @SerializedName("8")
        var x8: Int,
        @SerializedName("9")
        var x9: Int
    )

    data class Store(
        @SerializedName("id")
        var id: Int,
        @SerializedName("store")
        var store: Store,
        @SerializedName("url")
        var url: String
    ) {
        data class Store(
            @SerializedName("domain")
            var domain: String,
            @SerializedName("games_count")
            var gamesCount: Int,
            @SerializedName("id")
            var id: Int,
            @SerializedName("image_background")
            var imageBackground: String,
            @SerializedName("name")
            var name: String,
            @SerializedName("slug")
            var slug: String
        )
    }

    data class Tag(
        @SerializedName("games_count")
        var gamesCount: Int,
        @SerializedName("id")
        var id: Int,
        @SerializedName("image_background")
        var imageBackground: String,
        @SerializedName("language")
        var language: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("slug")
        var slug: String
    )
}