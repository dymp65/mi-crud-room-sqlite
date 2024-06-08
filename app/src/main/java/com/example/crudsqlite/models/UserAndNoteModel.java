package com.example.crudsqlite.models;

import androidx.room.Embedded;
import androidx.room.Relation;

public class UserAndNoteModel {
    @Embedded public UserModel user;
    @Relation(
            parentColumn = "id",
            entityColumn = "userId")
    @Embedded public NoteModel note;
}
