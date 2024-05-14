package com.example.dilshanpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version); // Use the parameters passed in
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Constants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS"+ Constants.TABLE_NAME);
        onCreate(db);
    }

    public long insertRecord(String image, String name, String age, String regNo, String score, String overs, String cent, String hCent, String wickets, String position){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.P_IMAGE, image);
        values.put(Constants.P_NAME, name);
        values.put(Constants.P_AGE, age);
        values.put(Constants.P_REGNO, regNo);
        values.put(Constants.P_SCORE, score);
        values.put(Constants.P_OVERS, overs);
        values.put(Constants.P_CENT, cent);
        values.put(Constants.P_HCENT, hCent);
        values.put(Constants.P_WICKETS, wickets);
        values.put(Constants.P_POSITION, position);

        long id = db.insert(Constants.TABLE_NAME, null, values);

        db.close();

        return id;
    }


    public Player getPlayer(String playerId) {
        Player player = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                Constants.P_ID,
                Constants.P_IMAGE,
                Constants.P_REGNO,
                Constants.P_NAME,
                Constants.P_AGE,
                Constants.P_SCORE,
                Constants.P_OVERS,
                Constants.P_CENT,
                Constants.P_HCENT,
                Constants.P_WICKETS,
                Constants.P_POSITION,
                // Add more columns as needed
        };
        String selection = Constants.P_REGNO + " = ?";
        String[] selectionArgs = { playerId };
        Cursor cursor = db.query(
                Constants.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (cursor != null && cursor.moveToFirst()) {
            // Retrieve player data from the cursor
            byte[] imageByteArray = cursor.getBlob(cursor.getColumnIndexOrThrow(Constants.P_IMAGE));
            Bitmap image = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
            // Rest of the data retrieval remains the same
            String regn = cursor.getString(cursor.getColumnIndexOrThrow(Constants.P_REGNO));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(Constants.P_NAME));
            String age = cursor.getString(cursor.getColumnIndexOrThrow(Constants.P_AGE));
            String score = cursor.getString(cursor.getColumnIndexOrThrow(Constants.P_SCORE));
            String overs = cursor.getString(cursor.getColumnIndexOrThrow(Constants.P_OVERS));
            String cent = cursor.getString(cursor.getColumnIndexOrThrow(Constants.P_CENT));
            String hcen = cursor.getString(cursor.getColumnIndexOrThrow(Constants.P_HCENT));
            String wicket = cursor.getString(cursor.getColumnIndexOrThrow(Constants.P_WICKETS));
            String position = cursor.getString(cursor.getColumnIndexOrThrow(Constants.P_POSITION));


            // Create a Player object with the retrieved data
            player = new Player(image, regn, name, age, score, overs, cent, hcen, wicket, position); // Adjust the constructor based on your Player class
        }
        if (cursor != null) {
            cursor.close();
        }
        return player;
    }

    public int updatePlayer(String regNo,String age, String score, String overs, String cent, String hCent, String wickets) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.P_AGE, age);
        values.put(Constants.P_SCORE, score);
        values.put(Constants.P_OVERS, overs);
        values.put(Constants.P_CENT, cent);
        values.put(Constants.P_HCENT, hCent);
        values.put(Constants.P_WICKETS, wickets);

        // Updating player record where REGNO matches
        return db.update(Constants.TABLE_NAME, values, Constants.P_REGNO + "=?", new String[]{regNo});
    }

    public int deletePlayer(String regNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Constants.TABLE_NAME, Constants.P_REGNO + "=?", new String[]{regNo});
}


}

