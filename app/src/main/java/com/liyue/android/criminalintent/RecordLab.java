package com.liyue.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.liyue.android.criminalintent.database.RecordBaseHelper;
import com.liyue.android.criminalintent.database.RecordCursorWrapper;
import com.liyue.android.criminalintent.database.RecordDbSchema.RecordTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by hasee on 2016/10/15.
 */

public class RecordLab {
    private static RecordLab sRecordLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static RecordLab get(Context context){
        if (sRecordLab == null){
            sRecordLab = new RecordLab(context);
        }
        return sRecordLab;
    }

    private RecordLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new RecordBaseHelper(mContext).getWritableDatabase();
    }

    public void updateRecord(Record r){
        String uuidString = r.getId().toString();
        ContentValues values = getContentValues(r);
        mDatabase.update(RecordTable.NAME, values,RecordTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public void addRecord(Record r) {
        ContentValues values = getContentValues(r);
        mDatabase.insert(RecordTable.NAME, null, values);
    }

    public void removeRecord(Record r){
        mDatabase.delete(RecordTable.NAME, RecordTable.Cols.UUID + " = ?", new String[]{r.getId().toString()});
    }

    public List<Record> getRecords(){
        List<Record> records = new ArrayList<Record>();
        RecordCursorWrapper cursor = queryRecords(null, null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                records.add(cursor.getRecord());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return records;
    }

    public Record getRecord(UUID id){
        RecordCursorWrapper cursor = queryRecords(RecordTable.Cols.UUID + " = ?", new String[]{id.toString()});
        try{
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getRecord();
        }finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(Record record){
        ContentValues values = new ContentValues();
        values.put(RecordTable.Cols.UUID, record.getId().toString());
        values.put(RecordTable.Cols.TITLE, record.getTitle());
        values.put(RecordTable.Cols.DATE, record.getDate().getTime());
        values.put(RecordTable.Cols.SOLVED, record.isSolved() ? 1 : 0);

        return values;
    }

    private RecordCursorWrapper queryRecords(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(RecordTable.NAME, null, whereClause, whereArgs, null, null, null);
        return new RecordCursorWrapper(cursor);
    }
}
