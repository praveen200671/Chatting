package com.decimal.parveen.chatting.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.decimal.parveen.chatting.pojochat.PojoChat;
import com.decimal.parveen.chatting.pojochat.PojoFriend;

import java.util.ArrayList;

/**
 * Created by PSAINI on 9/18/2017.
 */

public class MyChatDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MessageData";

    // Contacts table name
    private static final String TABLE_FRIENDS = "Friends";
    private static final String TABLE_MESSAGE = "Message";
    //    private String chatterName="";
//    private String msg ="";
//    private boolean isSentMsg =true;
//    private Date msgtime= new Date();
    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_UNIQUEID = "uniqueid";
    private static final String KEY_CHATTERNAME = "chatterName";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_ISSENTMESSAGE = "ismessagesent";
    private static final String KEY_MSGTIME = "msgtime";
    private static final String KEY_FRIENDID ="friend_id" ;


    public MyChatDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table for Friends list.......
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_FRIENDS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_CHATTERNAME + " TEXT," + KEY_UNIQUEID + " TEXT" + ")";

        // Table for storing for messages......
        String CREATE_MESSAGE_TABLE = "CREATE TABLE " + TABLE_MESSAGE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_FRIENDID + " TEXT," + KEY_MESSAGE + " TEXT,"
                + KEY_ISSENTMESSAGE + " TEXT," + KEY_MSGTIME + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_MESSAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addFriend(PojoFriend msg) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CHATTERNAME, msg.getFriendname());
        values.put(KEY_UNIQUEID, msg.getFrienduniqueid());

        // Inserting Row
        db.insert(TABLE_FRIENDS, null, values);
        db.close(); // Closing database connection
    }


    public ArrayList<PojoFriend> getFriendsList() {
        ArrayList<PojoFriend> friendlist = new ArrayList<>();
        PojoFriend frnd = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_FRIENDS, new String[]{KEY_ID, KEY_CHATTERNAME,
                    KEY_UNIQUEID}, null, null, null, null, KEY_CHATTERNAME);
            if (cursor != null)
                cursor.moveToFirst();
            do {
                try {
                    frnd = new PojoFriend();
                    frnd.setId(cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)) + "");
                    frnd.setFriendname(cursor.getString(cursor.getColumnIndexOrThrow(KEY_CHATTERNAME)));
                    frnd.setFrienduniqueid(cursor.getString(cursor.getColumnIndexOrThrow(KEY_UNIQUEID)));
                    friendlist.add(frnd);
                } catch (Exception e) {
                    e.getMessage();
                }
            } while (cursor.moveToNext());
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return friendlist;
    }

    public void addMessage(PojoChat msg) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FRIENDID, msg.getFriendid());
        values.put(KEY_MESSAGE, msg.getMsg());
        values.put(KEY_ISSENTMESSAGE, msg.getIsSentMsg());
        values.put(KEY_MSGTIME, msg.getMsgtime());

        // Inserting Row
        db.insert(TABLE_MESSAGE, null, values);
        db.close(); // Closing database connection
    }

    // get all message currently we have no separate contacts. We need to pass user id in this method.... to get specific user msg.
    public ArrayList<PojoChat> getMSG(String friendid) {
        ArrayList<PojoChat> chatmsglist = new ArrayList<>();
        PojoChat message = null;
        Cursor cursor;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            if (friendid.trim().length() != 0)
                cursor = db.query(TABLE_MESSAGE, new String[]{KEY_ID, KEY_FRIENDID,
                        KEY_MESSAGE, KEY_ISSENTMESSAGE, KEY_MSGTIME}, KEY_FRIENDID + "=?", new String[]{friendid}, null, null, KEY_ID);
            else
                cursor = db.query(TABLE_MESSAGE, new String[]{KEY_ID, KEY_FRIENDID,
                        KEY_MESSAGE, KEY_ISSENTMESSAGE, KEY_MSGTIME}, null, null, null, null, KEY_ID);
            if (cursor != null)
                cursor.moveToFirst();
            do {
                try {
                    message = new PojoChat();
                    message.setId(cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)) + "");
                    message.setFriendid(cursor.getString(cursor.getColumnIndexOrThrow(KEY_FRIENDID)));
                    message.setMsg(cursor.getString(cursor.getColumnIndexOrThrow(KEY_MESSAGE)));
                    message.setIsSentMsg(cursor.getString(cursor.getColumnIndexOrThrow(KEY_ISSENTMESSAGE)));
                    message.setMsgtime(cursor.getString(cursor.getColumnIndexOrThrow(KEY_MSGTIME)));
                    chatmsglist.add(message);
                } catch (Exception e) {
                    e.getMessage();
                }
            } while (cursor.moveToNext());
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chatmsglist;
    }

    public ArrayList<PojoFriend> getFriend(String mobileno) {
        ArrayList<PojoFriend> friendlist = new ArrayList<>();
        PojoFriend frnd = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_FRIENDS, new String[]{KEY_ID, KEY_CHATTERNAME,
                    KEY_UNIQUEID}, KEY_UNIQUEID+"=?", new String[]{mobileno}, null, null, KEY_CHATTERNAME);
            if (cursor != null)
                cursor.moveToFirst();
            do {
                try {
                    frnd = new PojoFriend();
                    frnd.setId(cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)) + "");
                    frnd.setFriendname(cursor.getString(cursor.getColumnIndexOrThrow(KEY_CHATTERNAME)));
                    frnd.setFrienduniqueid(cursor.getString(cursor.getColumnIndexOrThrow(KEY_UNIQUEID)));
                    friendlist.add(frnd);
                } catch (Exception e) {
                    e.getMessage();
                }
            } while (cursor.moveToNext());
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return friendlist;
    }
}
