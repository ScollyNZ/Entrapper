package nz.co.scoltock.entrapper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class TrapDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "entrapper.db";
   // public static final String SQL_CREATE_ENTRIES = "INSERT INTO "

    public TrapDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TrapContract.Trap.SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(TrapContract.Trap.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public List<TrapContract.Trap> getAllTraps() {
        List<TrapContract.Trap> traps = new LinkedList<TrapContract.Trap>();

        // 1. build the query
        String query = TrapContract.Trap.SQL_SELECT_ALL_TRAPS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        if (cursor.moveToFirst()) do {
            TrapContract.Trap trap = new TrapContract.Trap(Integer.parseInt(cursor.getString(0)), cursor.getString(1)) {
            };

            // Add book to books
            traps.add(trap);
        } while (cursor.moveToNext());

        Log.d("getAllTraps()", traps.toString());

        // return books
        return traps;
    }
}