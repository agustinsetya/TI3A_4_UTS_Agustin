package com.example.user.sqlite;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.sqlite.model.Kota;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Kota> kotaList = new ArrayList<>();

    public MyAdapter(List<Kota> kotaList) {
        this.kotaList = kotaList;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detail, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        holder.txt_resultnama.setText(kotaList.get(position).getKota());

    }

    @Override
    public int getItemCount() {
        return kotaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_resultnama;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_resultnama = (TextView) itemView.findViewById(R.id.txtHasilKota);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
