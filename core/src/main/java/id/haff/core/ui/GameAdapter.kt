package id.haff.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.haff.core.R
import id.haff.core.databinding.ItemGameBinding
import id.haff.core.domain.model.Game
import id.haff.core.utils.OnItemClickCallback

class GameAdapter: RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    var listData: List<Game>
        get() = differ.currentList
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            differ.submitList(value)
            notifyDataSetChanged()
        }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapter.ViewHolder =
        ViewHolder(
            ItemGameBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: GameAdapter.ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ViewHolder(val binding: ItemGameBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(data: Game) {
            with(binding) {
                if (data.image.isNotEmpty()){
                    Glide.with(itemView.context)
                        .load(data.image)
                        .into(ivImage)
                } else {
                    ivImage.setImageDrawable(itemView.context.getDrawable(R.drawable.no_image))
                }
                tvTitle.text = data.name
                tvRelease.text = data.releaseDate

                binding.cvRoot.setOnClickListener {
                    onItemClickCallback?.onItemClick(this@ViewHolder, data)
                }
            }
        }

    }
}