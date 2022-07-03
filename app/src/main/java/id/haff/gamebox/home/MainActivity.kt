package id.haff.gamebox.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import id.haff.core.data.Resource
import id.haff.core.domain.model.Game
import id.haff.core.ui.GameAdapter
import id.haff.core.utils.OnItemClickCallback
import id.haff.gamebox.R
import id.haff.gamebox.databinding.ActivityMainBinding
import id.haff.gamebox.detail.DetailActivity
import id.haff.gamebox.search.SearchActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel : MainViewModel by viewModels()
    private lateinit var gameAdapter: GameAdapter

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gameAdapter = GameAdapter()
        gameAdapter.setOnItemClickCallback(object : OnItemClickCallback{
            override fun onItemClick(view: RecyclerView.ViewHolder, objects: Any) {
                val holder = view as GameAdapter.ViewHolder
                val data = objects as Game
                toDetail(holder, data)
            }
        })

        viewModel.games.observe(this){
            when(it){
                is Resource.Loading -> {
                    binding.loadingView.viewBlock.visibility = View.VISIBLE
                    binding.errorView.viewBlock.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.loadingView.viewBlock.visibility = View.GONE
                    gameAdapter.listData = it.data!!
                }
                is Resource.Error -> {
                    binding.errorView.tvMessage.text = it.message
                    binding.errorView.viewBlock.visibility = View.VISIBLE
                    binding.loadingView.viewBlock.visibility = View.GONE
                }
            }
        }

        with(binding.rvGame) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = gameAdapter
        }

        binding.ivMore.setOnClickListener {
            val popupMenu = PopupMenu(this@MainActivity, it)
            popupMenu.setOnMenuItemClickListener { et ->
                when(et.itemId){
                    R.id.change_language -> this@MainActivity.startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                }
                true
            }
            popupMenu.inflate(R.menu.main_menu)
            popupMenu.show()
        }

        binding.ivFavorite.setOnClickListener {
            val uri = Uri.parse("haff://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        binding.ivSearch.setOnClickListener {
            startActivity(Intent(this@MainActivity, SearchActivity::class.java))
        }
    }

    private fun toDetail(holder: GameAdapter.ViewHolder, data: Game){
        val optionsCompat: ActivityOptionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@MainActivity,
                Pair(holder.binding.ivImage, "ivImage"),
                Pair(holder.binding.tvTitle, "tvTitle"),
                Pair(holder.binding.tvRelease, "tvRelease")
            )

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, data)
        startActivity(intent, optionsCompat.toBundle())
    }
}