package id.haff.favorite.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import id.haff.favorite.FavoriteActivity
import id.haff.gamebox.di.FavoriteModuleDependencies

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteModule {
    fun inject(activity: FavoriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(FavoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteModule
    }
}