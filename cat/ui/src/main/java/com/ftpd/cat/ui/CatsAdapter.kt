package com.ftpd.cat.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ftpd.base.Cat

class CatsAdapter : ListAdapter<Cat, CatsAdapter.ViewHolder>(
    AsyncDifferConfig.Builder(PostsDiffUtil()).build()
) {


    private class PostsDiffUtil : DiffUtil.ItemCallback<Cat>() {
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: Cat, newItem: Cat): Any {
            return Change(
                oldItem, newItem
            )
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_cell, parent, false))
    }

    override fun getItemCount(): Int {
        return currentList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindPostCell(currentList[position])

    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: AppCompatImageView = view.findViewById(R.id.imageView)

        fun bindPostCell(cat: Cat) {
            Glide.with(view.context).load(cat.url).centerCrop().into(imageView)

        }
    }


}