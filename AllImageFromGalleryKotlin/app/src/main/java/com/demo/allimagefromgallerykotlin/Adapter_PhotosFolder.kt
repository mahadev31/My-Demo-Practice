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


class Adapter_PhotosFolder(context: Context, al_menu: ArrayList<ModelClass>) :
    ArrayAdapter<ModelClass?>(context, R.layout.adapter_photosfolder, al_menu as List<ModelClass?>) {
    var context: Context
    var viewHolder: ViewHolder? = null
    var al_menu: ArrayList<ModelClass> = ArrayList<ModelClass>()

    init {
        this.al_menu = al_menu
        this.context = context
    }

    override fun getCount(): Int {
        Log.e("ADAPTER LIST SIZE", al_menu.size.toString() + "")
        return al_menu.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getViewTypeCount(): Int {
        return if (al_menu.size > 0) {
            al_menu.size
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
            viewHolder!!.tv_foldern = convertView!!.findViewById<View>(R.id.tv_folder) as TextView?
            viewHolder!!.tv_foldersize =
                convertView.findViewById<View>(R.id.tv_folder2) as TextView?
            viewHolder!!.iv_image = convertView.findViewById<View>(R.id.iv_image) as ImageView?
            convertView.setTag(viewHolder)
        } else {
            viewHolder = convertView.tag as ViewHolder
        }
        viewHolder!!.tv_foldern!!.text = al_menu[position].folderName
        viewHolder!!.tv_foldersize!!.setText(al_menu[position].imagePath.size  +"")
        Glide.with(context).load(al_menu[position].imagePath[0])
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(viewHolder!!.iv_image!!)
        return convertView!!
    }

    private class ViewHolder {
        var tv_foldern: TextView? = null
        var tv_foldersize: TextView? = null
        var iv_image: ImageView? = null
    }
}