package boni.sample.kotlin.library.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import boni.sample.java.R

/**
 * 데이터를 bind 할 수 있는 viewHolder
 */
class PagingViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.paging_item, parent, false)) {

    private val nameView = itemView.findViewById<TextView>(R.id.name)
    var paging : Paging? = null

    fun bindTo(paging: Paging?) {
        this.paging = paging
        nameView.text = paging?.name
    }
}
