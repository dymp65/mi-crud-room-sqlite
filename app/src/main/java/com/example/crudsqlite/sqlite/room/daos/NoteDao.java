package com.example.crudsqlite.sqlite.room.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.crudsqlite.models.NoteModel;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM notes")
    List<NoteModel> index();

    @Query("SELECT * FROM notes WHERE title LIKE :query OR subTitle LIKE :query")
    List<NoteModel> search(String query);
    @Query("SELECT * FROM notes WHERE id = :id")
    NoteModel show(int id);

    @Insert(entity = NoteModel.class, onConflict = OnConflictStrategy.REPLACE)
    void store(NoteModel person);

    @Insert(entity = NoteModel.class, onConflict = OnConflictStrategy.REPLACE)
    void storeAll(NoteModel... persons);

    @Update(entity = NoteModel.class, onConflict = OnConflictStrategy.REPLACE)
    void edit(NoteModel person);

    @Delete(entity = NoteModel.class)
    void destroy(NoteModel person);
}
