package id.haff.gamebox.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.haff.core.domain.model.Game
import id.haff.core.domain.usecase.GameUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val gameUseCase: GameUseCase): ViewModel() {
    fun getDetailGame(game: Game) = gameUseCase.getDetailGame(game).asLiveData()

    fun setFavorite(game: Game, isFavorite: Boolean) = gameUseCase.setFavoriteGame(game, isFavorite)
}