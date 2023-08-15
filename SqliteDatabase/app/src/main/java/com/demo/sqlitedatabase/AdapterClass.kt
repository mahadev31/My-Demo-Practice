package com.demo.sqlitedatabase

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class AdapterClass(var context: Context) : RecyclerView.Adapter<AdapterClass.MyViewHolder>() {
    var list = ArrayList<ModelClass>()
    var db = SQLiteDatabase(context)

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView = itemView.findViewById(R.id.txtIdDisplay)
        var name: TextView = itemView.findViewById(R.id.txtNameDisplay)
        var number: TextView = itemView.findViewById(R.id.txtNumberDisplay)
        var edit: AppCompatButton = itemView.findViewById(R.id.btnEdit)
        var delete: AppCompatButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.rcv_item_list, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.id.text = list[position].id.toString()
        holder.name.text = list[position].name
        holder.number.text = list[position].number

        holder.edit.setOnClickListener {
            var i = Intent(context, AddDataActivity::class.java)
            i.putExtra("id", list[position].id)
            i.putExtra("name", list[position].name)
            i.putExtra("number", list[position].number)
            i.putExtra("updateRecord", true)
            context.startActivity(i)
        }
        holder.delete.setOnClickListener {
            db.deleteData(list[position].id)

            deleteFun(list[position].id)
            Toast.makeText(context, "Data Delete Success", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteFun(id: Int) {
        list.removeAt(id)
        notifyItemRemoved(id)
        notifyDataSetChanged()
    }

    fun updateList(list: ArrayList<ModelClass>) {
        this.list = list
        notifyDataSetChanged()
    }
}