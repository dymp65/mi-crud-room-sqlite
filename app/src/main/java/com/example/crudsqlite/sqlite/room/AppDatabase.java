package com.example.crudsqlite.sqlite.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.crudsqlite.models.NoteModel;
import com.example.crudsqlite.sqlite.room.daos.NoteDao;

@Database(entities = {NoteModel.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance() {
        return instance;
    }

    public static void createDatabase(Context context) {
        instance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase.class,
                        "my_note_app"
                )
                .allowMainThreadQueries()
                .build();
    }
}
