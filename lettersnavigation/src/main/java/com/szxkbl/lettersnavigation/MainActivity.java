package com.szxkbl.lettersnavigation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    String[] mChar = {"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", ""};
    private ListView    mLv;
    private LettersView mLettersView;

    String[] mNames = {"张三", "李四", "李七", "刘某人", "王五", "Android", "IOS", "王寡妇",
            "阿三", "爸爸", "妈妈", "CoCo", "弟弟", "尔康", "紫薇", "小燕子", "皇阿玛",
            "福尔康", "哥哥", "Hi", "I", "杰克", "克星", "乐乐", "你好", "Oppo", "皮特", "曲奇饼",
            "日啊", "思思", "缇娜", "U", "V", "王大叔", "嘻嘻", "一小伙子", "撒贝宁",
            "吱吱", "舅舅", "老总", "隔壁老王", "许仙"};

    List<Bean> mList = new ArrayList<>();
    private int selection;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLettersView = (LettersView) findViewById(R.id.id_letter_view);
        TextView textView = (TextView) findViewById(R.id.id_tv_toast);
        mLettersView.setStrChars(mChar);
        mLettersView.setmTextView(textView);
        mLettersView.setOnLettersListViewListener(new LettersView.OnLettersListViewListener() {
            @Override
            public void onLettersListener(String s) {
                Log.e(TAG, "onLettersListener: " + selection);
                int selection = mAdapter.getPositionForNmae(s.charAt(0));
                mLv.setSelection(selection);
            }
        });
        for (int i = 0; i < mNames.length; i++) {
            String s = Trans2PinYin.trans2PinYin(mNames[i]);
            //            Log.e(TAG, "onCreate: " + s);
            mList.add(new Bean(mNames[i], s.toUpperCase()));
        }
        Collections.sort(mList, new MyComparator());
        mLv = (ListView) findViewById(R.id.id_lv);
        mAdapter = new MyAdapter(getApplicationContext(), mList);
        mLv.setAdapter(mAdapter);
    }

}
