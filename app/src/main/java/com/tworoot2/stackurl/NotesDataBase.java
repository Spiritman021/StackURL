package com.tworoot2.stackurl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.appcompat.view.menu.ListMenuItemView;

import java.util.ArrayList;
import java.util.List;

public class NotesDataBase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "notesDB";
    private static final String DATABASE_TABLE = "notesTable";

    // columns name for database table
    private static final String KEY_ID = "id";
    private static final String KEY_LINK = "link";


    NotesDataBase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + DATABASE_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_LINK + " TEXT" + ")";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion >= newVersion){
            return;
        }
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);

    }

    public long addNote(myModel note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(KEY_LINK,note.getLink());

        long ID = db.insert(DATABASE_TABLE,null,c);
        Log.d("Inserted", "ID = " + ID);
        return ID;

    }

    public myModel getNote(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE, new String[]{KEY_ID,KEY_LINK},KEY_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        return new myModel(cursor.getLong(0), cursor.getString(1));

    }

    public List<myModel> getNotes(){
        SQLiteDatabase db = this.getReadableDatabase();

        List<myModel> allNotes = new ArrayList<>();
        String query = "SELECT * FROM " + DATABASE_TABLE;
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                myModel note = new myModel();
                note.setID(cursor.getLong(0));
                note.setLink(cursor.getString(1));

                allNotes.add(note);

            }while (cursor.moveToNext());
        }

        return allNotes;
    }

    public long updateLink(myModel notes){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LINK, notes.getLink());
//        values.put(Params.KEY_PHONE, notes.getPhoneNumber());

        //Lets update now
        return db.update(DATABASE_TABLE, values, KEY_ID + "=?",
                new String[]{String.valueOf(notes.getID())});


    }
    public void deleteLink(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE,KEY_ID + "=?",new String[]{String.valueOf(id)});
        db.close();
    }

}
