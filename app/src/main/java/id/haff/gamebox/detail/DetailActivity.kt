package id.haff.gamebox.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import id.haff.core.data.Resource
import id.haff.core.domain.model.Game
import id.haff.gamebox.R
import id.haff.gamebox.databinding.ActivityDetailBinding

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }
    private val viewModel : DetailViewModel by viewModels()

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra(EXTRA_DATA)){
            val game = intent.getParcelableExtra<Game>(EXTRA_DATA)
            if (game != null){
                viewModel.getDetailGame(game).observe(this){
                    when(it){
                        is Resource.Loading -> {
                            binding.loadingView.viewBlock.visibility = View.VISIBLE
                            binding.errorView.viewBlock.visibility = View.GONE
                        }
                        is Resource.Success -> {
                            binding.loadingView.viewBlock.visibility = View.GONE
                            setDetailGame(it.data!!)
                        }
                        is Resource.Error -> {
                            binding.errorView.tvMessage.text = it.message
                            binding.errorView.viewBlock.visibility = View.VISIBLE
                            binding.loadingView.viewBlock.visibility = View.GONE
                        }
                    }
                }
            }
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setDetailGame(game: Game){
        binding.apply {

            if (game.image.isNotEmpty()){
                Glide.with(this@DetailActivity)
                    .load(game.image)
                    .into(ivImage)
            } else {
                ivImage.setImageDrawable(getDrawable(id.haff.core.R.drawable.no_image))
            }


            tvTitle.text = game.name
            tvRelease.text = game.releaseDate
            tvDetails.text = game.description

            var statusFavorite = game.isFavorite
            setStatusFavorite(statusFavorite)

            binding.fabLike.setOnClickListener {
                statusFavorite = !statusFavorite
                viewModel.setFavorite(game, statusFavorite)
                setStatusFavorite(statusFavorite)

                if (statusFavorite){
                    Snackbar.make(binding.root, getString(R.string.success_add_favorite, game.name), Snackbar.LENGTH_SHORT).show()
                }else {
                    Snackbar.make(binding.root, getString(R.string.success_removed_favorite, game.name), Snackbar.LENGTH_SHORT).show()
                }
            }

            btnOpenWebsite.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(game.url)
                startActivity(intent)
            }

            ivShare.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, getString(R.string.share_game, game.name, game.url))
                    putExtra(Intent.EXTRA_TITLE, game.name)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }

            Log.d("ERROR", "URL ${game.url}")
            if (game.url.isNullOrEmpty()){
                btnOpenWebsite.visibility = View.GONE
            }else {
                btnOpenWebsite.visibility = View.VISIBLE
            }

            ivImage.visibility = View.VISIBLE
            tvOverview.visibility = View.VISIBLE
            tvTitle.visibility = View.VISIBLE
            tvRelease.visibility = View.VISIBLE
            tvDetails.visibility = View.VISIBLE
            fabLike.visibility = View.VISIBLE
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabLike.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_like_fill))
        } else {
            binding.fabLike.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_like))
        }
    }

}