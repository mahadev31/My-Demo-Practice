package com.demo.wallpaperapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.wallpaperapi.model.Photo
import com.demo.wallpaperapi.R
import com.demo.wallpaperapi.databinding.RcvProductsListBinding

class AdapterClass(
    var context: Context,
    var productList: ArrayList<Photo>?,
    var click: (String) -> Unit
) :
    RecyclerView.Adapter<AdapterClass.MyViewHolder>() {

    class MyViewHolder(binding: RcvProductsListBinding) : RecyclerView.ViewHolder(binding.root) {
        //        var ids = binding.txtId
        var title = binding.txtTitle
        var imgProducts = binding.imgProducts
        var cdView = binding.cdView


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RcvProductsListBinding.inflate(LayoutInflater.from(context)))
    }

    override fun getItemCount(): Int {
        return productList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = productList!![position].title.toString()


        Glide.with(context)
            .load(productList!![position].url)
            .placeholder(
                R.drawable.ic_image
            ).into(holder.imgProducts)

        holder.cdView.setOnClickListener {
            click.invoke(productList!![position].url)

        }
    }
}