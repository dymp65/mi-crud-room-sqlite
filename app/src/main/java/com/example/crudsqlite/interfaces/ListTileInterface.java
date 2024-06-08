package com.example.crudsqlite.interfaces;

import com.example.crudsqlite.models.NoteModel;

public interface ListTileInterface {
    void onEdit(NoteModel noteModel);
    void onDelete(NoteModel noteModel);
}
