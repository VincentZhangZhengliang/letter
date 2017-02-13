package com.szxkbl.io;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.id_et);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(load())) {
            Log.e(TAG, "onResume: " + load());
            mEditText.setText(load());
            mEditText.setSelection(load().length());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        save(mEditText.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        save(mEditText.getText().toString());
    }

    private void save(String data) {
        FileOutputStream output = null;
        BufferedWriter writer = null;
        try {
            output = openFileOutput("data", MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(output));
            writer.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private String load() {
        FileInputStream input = null;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            input = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(input));
            String line = "";
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }
}
