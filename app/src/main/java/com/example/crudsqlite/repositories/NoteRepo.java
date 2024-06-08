package com.example.crudsqlite.repositories;

import com.example.crudsqlite.Env;
import com.example.crudsqlite.models.NoteModel;
import com.example.crudsqlite.sqlite.openhelper.DatabaseHelper;
import com.example.crudsqlite.sqlite.openhelper.dao.NoteDao;
import com.example.crudsqlite.sqlite.room.AppDatabase;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoteRepo {
    public interface NoteRepoInterface {
        void indexCallback(List<NoteModel> notes);
        void addCallback(NoteModel noteModel);
        void updateCallback(NoteModel noteModel);
        void deleteCallback(NoteModel noteModel);
    }

    public NoteRepoInterface noteRepoInterface;

    public void index() {
        if (Env.Database.ROOM == Env.database) {
            List<NoteModel> notes = AppDatabase.getInstance().noteDao().index();
            noteRepoInterface.indexCallback(notes);
        } else if (Env.Database.SQLITE == Env.database) {
            List<NoteModel> notes = new NoteDao().index();
            noteRepoInterface.indexCallback(notes);
        }
    }

    public void add(NoteModel noteModel) {
        if (Env.Database.ROOM == Env.database) {
            AppDatabase.getInstance().noteDao().store(noteModel);
            noteRepoInterface.addCallback(noteModel);
        } else if (Env.Database.SQLITE == Env.database) {
            new NoteDao().store(noteModel);
            noteRepoInterface.addCallback(noteModel);
        }
    }

    public void update(NoteModel noteModel) {
        if (Env.Database.ROOM == Env.database) {
            AppDatabase.getInstance().noteDao().edit(noteModel);
            noteRepoInterface.updateCallback(noteModel);
        } else if (Env.Database.SQLITE == Env.database) {
            new NoteDao().update(noteModel);
            noteRepoInterface.updateCallback(noteModel);
        }
    }

    public void delete(NoteModel noteModel) {
        if (Env.Database.ROOM == Env.database) {
            AppDatabase.getInstance().noteDao().destroy(noteModel);
            noteRepoInterface.deleteCallback(noteModel);
        } else if (Env.Database.SQLITE == Env.database) {
            new NoteDao().destroy(noteModel);
            noteRepoInterface.deleteCallback(noteModel);
        }
    }
}
