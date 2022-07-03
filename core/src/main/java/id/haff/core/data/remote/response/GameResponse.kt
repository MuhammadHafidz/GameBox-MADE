package id.haff.core.data.remote.response


import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("count")
    var count: Int,
    @SerializedName("description")
    var description: String,
    @SerializedName("filters")
    var filters: Filters,
    @SerializedName("next")
    var next: String,
    @SerializedName("nofollow")
    var nofollow: Boolean,
    @SerializedName("nofollow_collections")
    var nofollowCollections: List<String>,
    @SerializedName("noindex")
    var noindex: Boolean,
    @SerializedName("previous")
    var previous: Any,
    @SerializedName("results")
    var results: List<Result>,
    @SerializedName("seo_description")
    var seoDescription: String,
    @SerializedName("seo_h1")
    var seoH1: String,
    @SerializedName("seo_keywords")
    var seoKeywords: String,
    @SerializedName("seo_title")
    var seoTitle: String
) {
    data class Filters(
        @SerializedName("years")
        var years: List<Year>
    ) {
        data class Year(
            @SerializedName("count")
            var count: Int,
            @SerializedName("decade")
            var decade: Int,
            @SerializedName("filter")
            var filter: String,
            @SerializedName("from")
            var from: Int,
            @SerializedName("nofollow")
            var nofollow: Boolean,
            @SerializedName("to")
            var to: Int,
            @SerializedName("years")
            var years: List<Year>
        ) {
            data class Year(
                @SerializedName("count")
                var count: Int,
                @SerializedName("nofollow")
                var nofollow: Boolean,
                @SerializedName("year")
                var year: Int
            )
        }
    }

    data class Result(
        @SerializedName("added")
        var added: Int,
        @SerializedName("added_by_status")
        var addedByStatus: AddedByStatus,
        @SerializedName("background_image")
        var backgroundImage: String?,
        @SerializedName("clip")
        var clip: Any,
        @SerializedName("dominant_color")
        var dominantColor: String,
        @SerializedName("esrb_rating")
        var esrbRating: EsrbRating,
        @SerializedName("genres")
        var genres: List<Genre>,
        @SerializedName("id")
        var id: Int,
        @SerializedName("metacritic")
        var metacritic: Int,
        @SerializedName("name")
        var name: String,
        @SerializedName("parent_platforms")
        var parentPlatforms: List<ParentPlatform>,
        @SerializedName("platforms")
        var platforms: List<Platform>,
        @SerializedName("playtime")
        var playtime: Int,
        @SerializedName("rating")
        var rating: Double,
        @SerializedName("rating_top")
        var ratingTop: Int,
        @SerializedName("ratings")
        var ratings: List<Rating>,
        @SerializedName("ratings_count")
        var ratingsCount: Int,
        @SerializedName("released")
        var released: String?,
        @SerializedName("reviews_count")
        var reviewsCount: Int,
        @SerializedName("reviews_text_count")
        var reviewsTextCount: Int,
        @SerializedName("saturated_color")
        var saturatedColor: String,
        @SerializedName("short_screenshots")
        var shortScreenshots: List<ShortScreenshot>,
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
        @SerializedName("updated")
        var updated: String,
        @SerializedName("user_game")
        var userGame: Any
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
            @SerializedName("requirements_en")
            var requirementsEn: RequirementsEn,
            @SerializedName("requirements_ru")
            var requirementsRu: RequirementsRu
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

            data class RequirementsEn(
                @SerializedName("minimum")
                var minimum: String,
                @SerializedName("recommended")
                var recommended: String
            )

            data class RequirementsRu(
                @SerializedName("minimum")
                var minimum: String,
                @SerializedName("recommended")
                var recommended: String
            )
        }

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

        data class ShortScreenshot(
            @SerializedName("id")
            var id: Int,
            @SerializedName("image")
            var image: String
        )

        data class Store(
            @SerializedName("id")
            var id: Int,
            @SerializedName("store")
            var store: Store
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
}