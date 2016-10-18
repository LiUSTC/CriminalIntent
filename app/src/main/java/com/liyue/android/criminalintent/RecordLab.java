package com.liyue.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by hasee on 2016/10/15.
 */

public class RecordLab {
    private static RecordLab sRecordLab;
    private List<Record> mRecords;

    public static RecordLab get(Context context){
        if (sRecordLab == null){
            sRecordLab = new RecordLab(context);
        }
        return sRecordLab;
    }

    private RecordLab(Context context){
        mRecords = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            Record record = new Record();
            record.setTitle("Record #" + i);
            record.setSolved(i % 2 == 0);
            mRecords.add(record);
        }
    }

    public List<Record> getCrimes(){
        return mRecords;
    }

    public Record getCrime(UUID id){
        for (Record record : mRecords){
            if (record.getId().equals(id)){
                return record;
            }
        }
        return null;
    }
}
