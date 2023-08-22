package com.demo.allimagefromgallerykotlin

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class GridViewAdapter(
    context: Context,
    var imageList: ArrayList<ModelClass>,
    var int_position: Int
) :
    ArrayAdapter<ModelClass>(context, R.layout.adapter_photosfolder, imageList) {

    var viewHolder: ViewHolder? = null
//    private var al_menu: ArrayList<ModelClass> = ArrayList<ModelClass>()


    override fun getCount(): Int {
        Log.e("ADAPTER LIST SIZE", imageList[int_position].imagePath.size.toString())
        return imageList[int_position].imagePath.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getViewTypeCount(): Int {
        return if (imageList[int_position].imagePath.size > 0) {
            imageList[int_position].imagePath.size
        } else {
            1
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            viewHolder = ViewHolder()
            convertView = LayoutInflater.from(getContext())
                .inflate(R.layout.adapter_photosfolder, parent, false)
            viewHolder!!.tv_foldern = convertView.findViewById<View>(R.id.tv_folder) as TextView
            viewHolder!!.tv_foldersize = convertView.findViewById<View>(R.id.tv_folder2) as TextView
            viewHolder!!.iv_image = convertView.findViewById<View>(R.id.iv_image) as ImageView
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }
        viewHolder!!.tv_foldern!!.visibility = View.GONE
        viewHolder!!.tv_foldersize!!.visibility = View.GONE
        viewHolder!!.iv_image?.let {
            Glide.with(context).load(imageList[int_position].imagePath[position])
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(it)
        }
        return convertView!!
    }

    class ViewHolder {
        var tv_foldern: TextView? = null
        var tv_foldersize: TextView? = null
        var iv_image: ImageView? = null
    }
}