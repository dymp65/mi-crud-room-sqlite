package com.example.crudsqlite;

public class Env {
    public static enum Database {
        SQLITE,
        ROOM,
    }

    public static Database database = Database.SQLITE;
}
