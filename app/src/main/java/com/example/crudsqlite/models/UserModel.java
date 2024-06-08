package com.example.crudsqlite.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(tableName = "users")
public class UserModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
}
