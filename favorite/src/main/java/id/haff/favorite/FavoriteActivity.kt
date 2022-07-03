package id.haff.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.EntryPointAccessors
import id.haff.core.domain.model.Game
import id.haff.core.ui.GameAdapter
import id.haff.core.utils.OnItemClickCallback
import id.haff.favorite.databinding.ActivityFavoriteBinding
import id.haff.favorite.di.DaggerFavoriteModule
import id.haff.favorite.di.ViewModelFactory
import id.haff.gamebox.detail.DetailActivity
import id.haff.gamebox.di.FavoriteModuleDependencies
import javax.inject.Inject


class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels {
        factory
    }

    private lateinit var gameAdapter: GameAdapter

    private var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DaggerFavoriteModule.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    this,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)


        gameAdapter = GameAdapter()
        gameAdapter.setOnItemClickCallback(object : OnItemClickCallback {
            override fun onItemClick(view: RecyclerView.ViewHolder, objects: Any) {
                val holder = view as GameAdapter.ViewHolder
                val data = objects as Game
                toDetail(holder, data)
            }
        })

        viewModel.games.observe(this){
            if (it.isNullOrEmpty()){
                binding.emptyView.viewBlock.visibility = View.VISIBLE
            } else {
                binding.emptyView.viewBlock.visibility = View.GONE
            }
            gameAdapter.listData = it

        }

        with(binding.rvGame) {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = gameAdapter
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun toDetail(holder: GameAdapter.ViewHolder, data: Game){
        val optionsCompat: ActivityOptionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@FavoriteActivity,
                Pair(holder.binding.ivImage, "ivImage"),
                Pair(holder.binding.tvTitle, "tvTitle"),
                Pair(holder.binding.tvRelease, "tvRelease")
            )

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, data)
        startActivity(intent, optionsCompat.toBundle())
    }
}