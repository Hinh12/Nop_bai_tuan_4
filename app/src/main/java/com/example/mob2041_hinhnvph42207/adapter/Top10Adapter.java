package com.example.mob2041_hinhnvph42207.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mob2041_hinhnvph42207.R;
import com.example.mob2041_hinhnvph42207.model.Sach;

import java.util.ArrayList;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.ViewHolder> {

    private Context context;
    private ArrayList<Sach> list;

    public Top10Adapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_top,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaSach.setText(String.valueOf(list.get(position).getMaSach()));
        holder.txtTenSach.setText(list.get(position).getTenSach());
        holder.txtSoLuong.setText(String.valueOf(list.get(position).getSoLanMuon()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaSach, txtTenSach, txtSoLuong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaSach = itemView.findViewById(R.id.Top_MS);
            txtTenSach = itemView.findViewById(R.id.Top_TS);
            txtSoLuong = itemView.findViewById(R.id.Top_SL);
        }
    }
}
