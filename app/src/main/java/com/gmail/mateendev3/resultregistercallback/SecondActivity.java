package com.gmail.mateendev3.resultregistercallback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    public void finish() {
       /* Intent intent1 = new Intent();
        intent1.putExtra("Namee", "Name: " + intent.getStringExtra("Name"));
        intent1.putExtra("Classs", "Class: " + intent.getStringExtra("Class"));
        intent1.putExtra("Orgg", "Org: " + intent.getStringExtra("Org"));
        intent1.putExtra("Batchh", "Batch: " + intent.getStringExtra("Batch"));*/
        Intent intent = getIntent();
        String fName = intent.getData().toString().trim();
        Intent intent1 = new Intent();
        intent1.setData(Uri.parse(fName));
        setResult(RESULT_OK, intent1);
        super.finish();
    }
}