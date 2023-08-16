package com.demo.sqlitedatabase.java_pro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.sqlitedatabase.R;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TextView id = itemView.findViewById(R.id.txtIdDisplay);
            TextView name = itemView.findViewById(R.id.txtNameDisplay);
            TextView number = itemView.findViewById(R.id.txtNumberDisplay);
            AppCompatButton edit = itemView.findViewById(R.id.btnEdit);
            AppCompatButton delete = itemView.findViewById(R.id.btnDelete);
        }
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_item_list, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {

        holder.
    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
