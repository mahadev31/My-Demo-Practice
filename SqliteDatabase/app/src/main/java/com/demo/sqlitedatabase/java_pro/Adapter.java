package com.demo.sqlitedatabase.java_pro;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.sqlitedatabase.R;
import com.demo.sqlitedatabase.java_pro.activity.InsertDataActivity;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    ArrayList<Model> list = new ArrayList<>();
    Context context;
    int total = 0;

    TotalAll_Interface totalAll;
    Database db = new Database(context);

    public Adapter(Context context, TotalAll_Interface totalAll) {
        this.context = context;
        this.totalAll = totalAll;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, itemName, price;
        AppCompatButton edit, delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.txtIdDisplay);
            itemName = itemView.findViewById(R.id.txtItemDisplay);
            price = itemView.findViewById(R.id.txtPriceDisplay);
            edit = itemView.findViewById(R.id.btnEdit);
            delete = itemView.findViewById(R.id.btnDelete);
        }
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_item_list, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.id.setText(String.valueOf(list.get(position).getId()));
        holder.itemName.setText(list.get(position).getItemName());
        holder.price.setText(list.get(position).getPrice());

        int totalAmount = Integer.parseInt(String.valueOf(holder.price.getText()));
        total = total + totalAmount;
        Log.e("TAG", "total: $total");

        if (position == list.size() - 1) {
             totalAll.totalAll();
        }


        holder.edit.setOnClickListener(v -> {
            Intent i = new Intent(context, InsertDataActivity.class);
            i.putExtra("id", list.get(position).getId());
            i.putExtra("itemName", list.get(position).getItemName());
            i.putExtra("price", list.get(position).getPrice());
            i.putExtra("updateRecord", true);
            context.startActivity(i);
        });
        holder.delete.setOnClickListener(v -> {
            deleteFun(position, list.get(position).getId());
        });
    }

    private void deleteFun(int position, int id) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_delete);

        Button btnSet = dialog.findViewById(R.id.btnSet);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        btnSet.setOnClickListener(v -> {
            db.deleteData(id);

            list.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, list.size());

            Toast.makeText(context, "delete record success", Toast.LENGTH_SHORT).show();

            dialog.dismiss();
        });

        btnCancel.setOnClickListener(v -> {

            Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));   //dialog box TRANSPARENT
        dialog.getWindow().setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(ArrayList<Model> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public int totalFunction() {

        Log.e("function", "total: " + total);
        return total;

    }
}


