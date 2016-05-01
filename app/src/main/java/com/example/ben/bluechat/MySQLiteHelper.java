package com.example.ben.bluechat;
/*Created by: Eduardo Pozo
*File Name: MySQLiteHelper
*Description: Announces columns and rows of the table where chat history will be stored.
* on creation we the 3 columns created are id, name, and chatLog where a user's information will be
* stored.  When storing a chat history update will check to see if the device already has an entry,
* otherwise, add it.
*Last Update: 04/29/2016
*/
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "chatLogDB.db";
    private static final String DATABASE_TABLE = "ChatLog_table";
    //Table column names
    private static final String KEY_ID = "id";
    private static final String DEVICE_NAME = "name";
    private static final String CHAT_LOG = "chatLog";
    //Variable to use on create
    private static final String TABLE_CREATE = "CREATE TABLE " + DATABASE_TABLE + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DEVICE_NAME + " TEXT, " + CHAT_LOG
            + " TEXT)";


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Deletes existing database if new one is created
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        // create fresh books table
        this.onCreate(db);
    }
    public void addChat(ChatHistory chat) {
        //Create values to insert into the table
        ContentValues values = new ContentValues();
        values.put(DEVICE_NAME, chat.getDeviceName());
        values.put(CHAT_LOG, chat.getMessageList());
        SQLiteDatabase db = this.getWritableDatabase();
        //add into table
        db.insert(DATABASE_TABLE, null, values);
        db.close();
    }
    //pull up chat log
    public ArrayList<String> getChat(String device) {
        //Get the reference for the DB
        String query = "Select * FROM " + DATABASE_TABLE +
                " WHERE " + DEVICE_NAME + " = \"" +
                device + "\"";
        SQLiteDatabase db =this.getReadableDatabase();
        //Build query
        Cursor cursor = db.rawQuery(query, null);
        //result if we get it
        ArrayList<String> returnChat = new ArrayList<String>();
        if(cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();
            ChatHistory oldChat = new ChatHistory();
            oldChat.setDeviceName(cursor.getString(cursor.getColumnIndex(DEVICE_NAME)));
            return oldChat.setMessageList(cursor.getString(2));
        } else {
            cursor.close();
            db.close();
            return returnChat;
        }
    }
    public void updateLog (ChatHistory update) {
        //result of the update action
        int result;
        //get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        //create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(DEVICE_NAME, update.getDeviceName()); // get title
        values.put(CHAT_LOG, update.getMessageList()); // get author
        //updating row
        result = db.update(DATABASE_TABLE, //table
                values, // column/value
                DEVICE_NAME+" = ?", // selections
                new String[] { update.getDeviceName() }); //selection args
        db.close();
        //in case a new row was not added because it doesn't exist, simply add the row
        if(result == 0)
            addChat(update);
    }
}