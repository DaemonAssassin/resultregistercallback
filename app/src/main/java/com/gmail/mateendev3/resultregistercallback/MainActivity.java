package com.gmail.mateendev3.resultregistercallback;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnGetResult;
    EditText etName, etClass, etOrg, etBatch;
    TextView tvName, tvClass, tvOrg, tvBatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetResult = findViewById(R.id.btn_get_result);

        etName = findViewById(R.id.et_Name);
        etClass = findViewById(R.id.et_class);
        etOrg = findViewById(R.id.et_org);
        etBatch = findViewById(R.id.et_batch);

        tvName = findViewById(R.id.tv_name);
        tvClass = findViewById(R.id.tv_class);
        tvOrg = findViewById(R.id.tv_org);
        tvBatch = findViewById(R.id.tv_batch);

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent intent = result.getData();
                            tvName.setText(intent.getStringExtra("Namee"));
                            tvClass.setText(intent.getStringExtra("Classs"));
                            tvOrg.setText(intent.getStringExtra("Orgg"));
                            tvBatch.setText(intent.getStringExtra("Batchh"));
                        }
                    }
                });


        btnGetResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("Name", etName.getText().toString());
                intent.putExtra("Class", etClass.getText().toString());
                intent.putExtra("Org", etOrg.getText().toString());
                intent.putExtra("Batch", etBatch.getText().toString());
                launcher.launch(intent);
            }
        });
    }
}