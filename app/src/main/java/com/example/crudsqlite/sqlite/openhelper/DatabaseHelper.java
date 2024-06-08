package com.example.crudsqlite.sqlite.openhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.crudsqlite.models.NoteModel;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "my_note_app2";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "notes";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SUB_TITLE = "subTitle";
    private static final String COLUMN_NOTE = "note";
    private static final String COLUMN_CREATED_AT = "createdAt";
    private static final String COLUMN_UPDATED_AT = "updatedAt";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TITLE + " TEXT, " +
            COLUMN_SUB_TITLE + " TEXT, " +
            COLUMN_NOTE + " TEXT, " +
            COLUMN_CREATED_AT + " TEXT, " +
            COLUMN_UPDATED_AT + " TEXT" +
            ")";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteDatabase open() {
        return this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    private static DatabaseHelper mDatabaseInstance = null;
    public static DatabaseHelper newInstance(Context context){
        if (mDatabaseInstance == null){
            mDatabaseInstance = new DatabaseHelper(context);
        }
        return mDatabaseInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
