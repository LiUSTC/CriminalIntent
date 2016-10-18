package com.liyue.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by hasee on 2016/10/15.
 */

public class RecordListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new RecordListFragment();
    }
}
