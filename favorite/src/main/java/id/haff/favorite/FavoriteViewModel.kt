package id.haff.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.haff.core.domain.usecase.GameUseCase
import javax.inject.Inject


class FavoriteViewModel @Inject constructor(gameUseCase: GameUseCase): ViewModel() {
    val games = gameUseCase.getFavoriteGames().asLiveData()
}