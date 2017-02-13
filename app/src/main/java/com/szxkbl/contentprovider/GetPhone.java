package com.szxkbl.contentprovider;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Vincent
 * @time : 2017/2/13 10:13.
 * @Discription :
 */

public class GetPhone {
    public static List<Bean> mBeen = new ArrayList<>();

    public static List<Bean> getPhone(Context context) {
        Cursor query = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        String name; //联系人
        String phone; //电话号码
        while (query.moveToNext()) {
            name = query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phone = query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            mBeen.add(new Bean(name, phone));
        }
        return mBeen;
    }
}
