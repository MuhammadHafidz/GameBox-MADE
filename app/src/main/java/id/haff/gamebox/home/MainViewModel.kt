package id.haff.gamebox.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.haff.core.domain.usecase.GameUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(gameUseCase: GameUseCase): ViewModel() {
    val games = gameUseCase.getAllGames().asLiveData()
}