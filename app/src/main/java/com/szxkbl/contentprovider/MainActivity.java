package com.szxkbl.contentprovider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Bean> mData = new ArrayList<>();
    private ListView mLv;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    1);

        } else {
            mData = GetPhone.getPhone(this);
            for (Bean bean : mData) {
                Log.e(TAG, "onCreate: " + bean);
            }
        }
        mLv = (ListView) findViewById(R.id.id_lv);
        mLv.setAdapter(mAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mData = GetPhone.getPhone(this);
                for (Bean bean : mData) {
                    Log.e(TAG, "onRequestPermissionsResult: " + bean);
                }
            } else {
                // Permission Denied
                Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private BaseAdapter mAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return mData == null ? 0 : mData.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.mName.setText(mData.get(i).getName());
            holder.mPhone.setText(mData.get(i).getPhone());
            return view;
        }
    };

    class ViewHolder {
        View mView;
        private final TextView mName;
        private final TextView mPhone;

        public ViewHolder(View view) {
            mView = view;
            mName = (TextView) mView.findViewById(R.id.item_tv_name);
            mPhone = (TextView) mView.findViewById(R.id.item_tv_phone);
        }
    }
}
