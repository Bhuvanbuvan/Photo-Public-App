package com.example.firebaseimageapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseimageapp.databinding.PostItemBinding
import com.example.firebaseimageapp.model.PostModel
import com.squareup.picasso.Picasso

class PostAdapter(context:Context):RecyclerView.Adapter<PostAdapter.MyViewHolder>() {
    inner class MyViewHolder(var binding: PostItemBinding):RecyclerView.ViewHolder(binding.root)

    private var differUtil=object :DiffUtil.ItemCallback<PostModel>(){
        override fun areItemsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
            return oldItem.title ==newItem.title &&
                    oldItem.discription==newItem.discription
        }

        override fun areContentsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
            return oldItem==newItem
        }

    }

    var differ=AsyncListDiffer(this,differUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.MyViewHolder {
        return MyViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context)
            ,parent,false))
    }

    override fun onBindViewHolder(holder: PostAdapter.MyViewHolder, position: Int) {
        var currentItem= differ.currentList[position]

        holder.binding.itemTitle.text=currentItem.title.toString()
        holder.binding.itemDiscription.text=currentItem.discription.toString()
        Picasso.get().load(currentItem.image.toString()).into(holder.binding.itemImage)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}