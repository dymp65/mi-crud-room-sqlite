package com.example.crudsqlite;

import com.example.crudsqlite.sqlite.openhelper.DatabaseHelper;
import com.example.crudsqlite.sqlite.room.AppDatabase;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the Prefs class
        // ROOM database
        AppDatabase.createDatabase(this);

        // SQLITE database
        DatabaseHelper.newInstance(this);
    }
}
