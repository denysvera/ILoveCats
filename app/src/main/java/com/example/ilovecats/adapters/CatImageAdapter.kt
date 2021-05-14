package com.example.ilovecats.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ilovecats.databinding.CatImageItemBinding
import com.example.ilovecats.databinding.CatImageItemBinding.inflate
import com.example.ilovecats.models.Cat

class CatImageAdapter (private val catImageListener: CatImageListener): PagingDataAdapter<Cat, CatImageAdapter.CatImageViewHolder>(CatImageDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatImageViewHolder {
       return CatImageViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CatImageViewHolder, position: Int) {
        holder.bind(getItem(position)!!,catImageListener)
    }


    class CatImageViewHolder constructor(var binding: CatImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            cat: Cat,
            catImageListener: CatImageListener
        ) {
            binding.cat = cat
            binding.clickListener = catImageListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CatImageViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = inflate(layoutInflater, parent, false)
                return CatImageViewHolder(binding)
            }
        }
    }


    companion object CatImageDiffCallback : DiffUtil.ItemCallback<Cat>() {
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem.id == newItem.id
        }

    }
    class CatImageListener(val clickListener: (catImage: Cat) -> Unit) {
        fun onClick(catImage: Cat) = clickListener(catImage)
    }


}