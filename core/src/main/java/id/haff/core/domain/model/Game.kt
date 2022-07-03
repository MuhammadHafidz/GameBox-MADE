package id.haff.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id: String,
    val name: String,
    val image: String,
    val description: String?,
    val url: String?,
    val releaseDate: String,
    val isFavorite: Boolean = false,
) : Parcelable
