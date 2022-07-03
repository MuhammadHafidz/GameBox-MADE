package id.haff.core.utils

import androidx.recyclerview.widget.RecyclerView

interface OnItemClickCallback {
    fun onItemClick(view: RecyclerView.ViewHolder, objects: Any)
}