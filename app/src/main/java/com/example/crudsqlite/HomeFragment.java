package com.example.crudsqlite;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.crudsqlite.adapters.HomeAdapter;
import com.example.crudsqlite.databinding.DialogAddNoteBinding;
import com.example.crudsqlite.databinding.FragmentHomeBinding;
import com.example.crudsqlite.interfaces.ListTileInterface;
import com.example.crudsqlite.models.NoteModel;
import com.example.crudsqlite.viewmodels.HomeVM;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements ListTileInterface {

    FragmentHomeBinding binding;
    HomeAdapter homeAdapter;
    HomeVM homeVM;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.toolbar.getMenu().findItem(R.id.menu_add).setOnMenuItemClickListener(item -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("Add Note");
            builder.setMessage("Please fill the form below");
            builder.setCancelable(true);
            DialogAddNoteBinding dialogAddNoteBinding = DialogAddNoteBinding.inflate(inflater);
            builder.setView(dialogAddNoteBinding.getRoot());
            builder.setPositiveButton("Save", (dialog, which) -> {
                NoteModel noteModel = new NoteModel();
                noteModel.setTitle(dialogAddNoteBinding.etTitle.getText().toString());
                noteModel.setSubTitle(dialogAddNoteBinding.etSubTitle.getText().toString());
                homeVM.add(noteModel);
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> {
                dialog.dismiss();
            });
            builder.show();
            return true;
        });

        homeAdapter = new HomeAdapter(requireContext(), new ArrayList<>(), this);
        binding.rv.setAdapter(homeAdapter);

        homeVM = new ViewModelProvider(getActivity()).get(HomeVM.class);
        homeVM.notesObs.observe(getViewLifecycleOwner(), noteModels -> {
            if (noteModels.isEmpty()) {
                binding.emptyView.lEmpty.setVisibility(View.VISIBLE);
            } else {
                binding.emptyView.lEmpty.setVisibility(View.INVISIBLE);
            }
            Log.i("HomeFragment", "onCreateView: " + noteModels.size());
            homeAdapter.setNotes(noteModels);
            homeAdapter.notifyDataSetChanged();
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeVM.index();
    }

    @Override
    public void onEdit(NoteModel noteModel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Edit Note");
        builder.setMessage("Please fill the form below");
        builder.setCancelable(true);
        DialogAddNoteBinding dialogAddNoteBinding = DialogAddNoteBinding.inflate(getLayoutInflater());
        dialogAddNoteBinding.etTitle.setText(noteModel.getTitle());
        dialogAddNoteBinding.etSubTitle.setText(noteModel.getSubTitle());
        builder.setView(dialogAddNoteBinding.getRoot());
        builder.setPositiveButton("Save", (dialog, which) -> {
            noteModel.setTitle(dialogAddNoteBinding.etTitle.getText().toString());
            noteModel.setSubTitle(dialogAddNoteBinding.etSubTitle.getText().toString());
            homeVM.update(noteModel);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
    }

    @Override
    public void onDelete(NoteModel noteModel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Delete Note");
        builder.setMessage("Are you sure you want to delete this note?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", (dialog, which) -> {
            homeVM.delete(noteModel);
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
    }
}