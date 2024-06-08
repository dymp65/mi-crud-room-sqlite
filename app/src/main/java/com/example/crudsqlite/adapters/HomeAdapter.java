package com.example.crudsqlite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudsqlite.databinding.ListTileBinding;
import com.example.crudsqlite.interfaces.ListTileInterface;
import com.example.crudsqlite.models.NoteModel;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    Context context;
    List<NoteModel> notes;
    ListTileInterface listTileInterface;

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListTileBinding binding = ListTileBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        NoteModel noteModel = notes.get(position);
        holder.binding.tvTitle.setText(noteModel.getTitle());
        holder.binding.tvSubtitle.setText(noteModel.getSubTitle());
        holder.binding.btnEdit.setOnClickListener(v -> {
            listTileInterface.onEdit(noteModel);
        });
        holder.binding.btnDelete.setOnClickListener(v -> {
            listTileInterface.onDelete(noteModel);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ListTileBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ListTileBinding.bind(itemView);
        }
    }
}
