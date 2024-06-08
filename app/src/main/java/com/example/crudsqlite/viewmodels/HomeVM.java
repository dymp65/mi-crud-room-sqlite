package com.example.crudsqlite.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.crudsqlite.models.NoteModel;
import com.example.crudsqlite.repositories.NoteRepo;
import com.example.crudsqlite.sqlite.room.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomeVM extends ViewModel implements NoteRepo.NoteRepoInterface {
    public MutableLiveData<List<NoteModel>> notesObs = new MutableLiveData<List<NoteModel>>() {{
        setValue(new ArrayList<>());
    }};

    public void index () {
        NoteRepo noteRepo = new NoteRepo(this);
        noteRepo.index();
    }

    public void add (NoteModel noteModel) {
        NoteRepo noteRepo = new NoteRepo(this);
        noteRepo.add(noteModel);
    }

    public void update (NoteModel noteModel) {
        NoteRepo noteRepo = new NoteRepo(this);
        noteRepo.update(noteModel);
    }

    public void delete (NoteModel noteModel) {
        NoteRepo noteRepo = new NoteRepo(this);
        noteRepo.delete(noteModel);
    }

    @Override
    public void indexCallback(List<NoteModel> notes) {
        notesObs.setValue(notes);
    }

    @Override
    public void addCallback(NoteModel noteModel) {
        index();
    }

    @Override
    public void updateCallback(NoteModel noteModel) {
        index();
    }

    @Override
    public void deleteCallback(NoteModel noteModel) {
        index();
    }
}
