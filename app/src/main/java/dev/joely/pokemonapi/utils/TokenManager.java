package dev.joely.pokemonapi.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TokenManager {

    private static final String DATABASE_NAME = "secure_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "token";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TOKEN = "access_token";
    private SQLiteDatabase database;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_TOKEN + " TEXT)";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public TokenManager(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void saveToken(String token) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, 1);
        values.put(COLUMN_TOKEN, token);
        database.replace(TABLE_NAME, null, values);
    }

    public String getToken() {
        Cursor cursor = database.query(TABLE_NAME, new String[]{COLUMN_TOKEN}, COLUMN_ID + " = ?", new String[]{"1"}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String token = cursor.getString(cursor.getColumnIndex(COLUMN_TOKEN));
            cursor.close();
            return token;
        }
        return null;
    }

    public void clearToken() {
        database.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{"1"});
    }
}
