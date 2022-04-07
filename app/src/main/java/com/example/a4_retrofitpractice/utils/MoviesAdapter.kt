package com.example.a4_retrofitpractice.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.a4_retrofitpractice.databinding.MoveItemBinding
import com.example.a4_retrofitpractice.model.ResponseMoviesList

class MoviesAdapter:RecyclerView.Adapter<MoviesAdapter.ViewHolder>(){
    private lateinit var binding: MoveItemBinding
    private lateinit var context:Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = MoveItemBinding.inflate(inflater,parent,false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: MoviesAdapter.ViewHolder, position: Int) {
       holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder:RecyclerView.ViewHolder(binding.root){
        fun bind(item:ResponseMoviesList.Data){
            binding.apply {
                textView.text = item.title
                imageView.load(item.poster)
            }
        }
    }

    val diffUtil = object :DiffUtil.ItemCallback<ResponseMoviesList.Data>(){
        override fun areItemsTheSame(oldItem: ResponseMoviesList.Data, newItem: ResponseMoviesList.Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResponseMoviesList.Data, newItem: ResponseMoviesList.Data): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,diffUtil)
}