package id.haff.gamebox.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.haff.core.domain.usecase.GameInteractor
import id.haff.core.domain.usecase.GameUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideGameUseCase(gameInteractor: GameInteractor): GameUseCase
}