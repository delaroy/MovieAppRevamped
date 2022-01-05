package com.delaroystudios.movieapp.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.delaroystudios.movieapp.data.model.Movie
import com.delaroystudios.movieapp.databinding.MovieCardBinding

class HomeAdpater(val context: Context, val recyclerViewHome: RecyclerViewHomeClickListener) : RecyclerView.Adapter<ViewHolder>(){
    private lateinit var recyclerView: RecyclerView
    lateinit var mActivity: AppCompatActivity

    private val TAG: String = "AppDebug"

    var items: List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items!!.get(position)
        item?.let {
            holder.apply {
                bind(item, isLinearLayoutManager())
                itemView.tag = item
            }
        }

        holder.itemView.setOnClickListener {
            recyclerViewHome.clickOnItem(
                item!!,
                holder.itemView
            )
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun getItemCount(): Int {
        if (items == null) {
            return 0
        } else {
            return items!!.size
        }
    }

    fun submitList(itemList: List<Movie>){
        items = itemList!!
        notifyDataSetChanged()
    }

    private fun isLinearLayoutManager() = recyclerView.layoutManager is LinearLayoutManager
}

class ViewHolder(private val binding: MovieCardBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Movie, isLinearLayoutManager: Boolean) {
        binding.apply {
            doc = item
            executePendingBindings()
        }
    }
}
interface RecyclerViewHomeClickListener {
    fun clickOnItem(data: Movie, card: View)
}
