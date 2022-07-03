package id.haff.gamebox.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import id.haff.core.data.Resource
import id.haff.core.domain.model.Game
import id.haff.core.ui.GameAdapter
import id.haff.core.utils.OnItemClickCallback
import id.haff.gamebox.databinding.ActivitySearchBinding
import id.haff.gamebox.detail.DetailActivity
import kotlinx.coroutines.*

@ExperimentalCoroutinesApi
@FlowPreview
@ObsoleteCoroutinesApi
@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private val viewModel : SearchViewModel by viewModels()
    private lateinit var gameAdapter: GameAdapter

    private var _binding: ActivitySearchBinding? = null
    private val binding get() = _binding!!

    private var searchWatcher: TextWatcher? = object : TextWatcher{
        override fun afterTextChanged(s: Editable?) {
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s.toString().isNotEmpty()){
                lifecycleScope.launch {
                    viewModel.queryChannel.send(s.toString())
                }
            } else {
                gameAdapter.listData = listOf()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        gameAdapter = GameAdapter()
        gameAdapter.setOnItemClickCallback(object : OnItemClickCallback {
            override fun onItemClick(view: RecyclerView.ViewHolder, objects: Any) {
                val holder = view as GameAdapter.ViewHolder
                val data = objects as Game
                toDetail(holder, data)
            }
        })

        binding.etSearch.addTextChangedListener(searchWatcher)

        viewModel.searchResult.observe(this){
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
            layoutManager = LinearLayoutManager(this@SearchActivity)
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
                this@SearchActivity,
                Pair(holder.binding.ivImage, "ivImage"),
                Pair(holder.binding.tvTitle, "tvTitle"),
                Pair(holder.binding.tvRelease, "tvRelease")
            )

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, data)
        startActivity(intent, optionsCompat.toBundle())
    }

    override fun onDestroy() {
        binding.etSearch.removeTextChangedListener(searchWatcher)
        searchWatcher = null
        super.onDestroy()
    }
}