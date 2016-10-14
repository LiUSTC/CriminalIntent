package com.liyue.android.criminalintent;

import java.util.UUID;

/**
 * Created by hasee on 2016/10/14.
 */

public class Crime {
    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Crime(){
        mId = UUID.randomUUID();
    }
    private UUID mId;
    private String mTitle;
}
