package com.example.crudsqlite.sqlite.openhelper.dao;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.crudsqlite.models.NoteModel;
import com.example.crudsqlite.sqlite.openhelper.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class NoteDao {

    @SuppressLint("Range")
    public List<NoteModel> index() {
        List<NoteModel> notes = new ArrayList<>();
        SQLiteDatabase db = DatabaseHelper.newInstance(null).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM notes", null);
        if (cursor.moveToFirst()) {
            do {
                NoteModel note = new NoteModel();
                note.setId(cursor.getInt(cursor.getColumnIndex("id")));
                note.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                note.setSubTitle(cursor.getString(cursor.getColumnIndex("subTitle")));
                note.setNote(cursor.getString(cursor.getColumnIndex("note")));
                notes.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }

    public void store(NoteModel noteModel) {
        SQLiteDatabase db = DatabaseHelper.newInstance(null).getWritableDatabase();
        db.execSQL("INSERT INTO notes (title, subTitle, note) VALUES ('" + noteModel.getTitle() + "', '" + noteModel.getSubTitle() + "', '" + noteModel.getNote() + "')");
        db.close();
    }

    public void update(NoteModel noteModel) {
        SQLiteDatabase db = DatabaseHelper.newInstance(null).getWritableDatabase();
        db.execSQL("UPDATE notes SET title = '" + noteModel.getTitle() + "', subTitle = '" + noteModel.getSubTitle() + "', note = '" + noteModel.getNote() + "' WHERE id = " + noteModel.getId());
        db.close();
    }

    public void destroy(NoteModel noteModel) {
        SQLiteDatabase db = DatabaseHelper.newInstance(null).getWritableDatabase();
        db.execSQL("DELETE FROM notes WHERE id = " + noteModel.getId());
        db.close();
    }
}
