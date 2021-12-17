package com.gmail.mateendev3.resultregistercallback;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DataBase extends AppCompatActivity {
    Button btnGetContact;
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

        ActivityResultLauncher<Void> myLauncher = registerForActivityResult(
                new ActivityResultContracts.PickContact(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        Toast.makeText(DataBase.this, "Contact: " + result, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        btnGetResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myLauncher.launch(null);
            }
        });

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new GetContent(),
                new ActivityResultCallback<Intent>() {
                    @Override
                    public void onActivityResult(Intent result) {
                        tvName.setText(result.getStringExtra("Namee"));
                        tvClass.setText(result.getStringExtra("Classs"));
                        tvOrg.setText(result.getStringExtra("Orgg"));
                        tvBatch.setText(result.getStringExtra("Batchh"));
                    }
                }
        );



        ActivityResultLauncher<String> launcherString = registerForActivityResult(
                new GetName(),
                new ActivityResultCallback<String>() {
                    @Override
                    public void onActivityResult(String result) {
                        Toast.makeText(DataBase.this, "Name: " + result, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        btnGetContact = findViewById(R.id.btnGetContact);
        btnGetResult.setOnClickListener(v -> {
            /*Intent intent = new Intent(DataBase.this, SecondActivity.class);
            intent.putExtra("Name", etName.getText().toString());
            intent.putExtra("Class", etClass.getText().toString());
            intent.putExtra("Org", etOrg.getText().toString());
            intent.putExtra("Batch", etBatch.getText().toString());
            launcher.launch(intent);*/

            launcherString.launch("            Mateen Mehmood          ");
        });

    }

    public static final class GetContent extends ActivityResultContract<Intent, Intent> {

        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, Intent input) {
            return input;
        }

        @Override
        public Intent parseResult(int resultCode, @Nullable Intent intent) {
            if (resultCode == RESULT_CANCELED) {
                return null;
            }
            return intent;
        }
    }
    public static final class GetName extends ActivityResultContract<String, String> {

        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, String input) {
            Intent intent = new Intent(context, SecondActivity.class);
            intent.setData(Uri.parse(input));
            return intent;
        }

        @Override
        public String parseResult(int resultCode, @Nullable Intent intent) {
            if (resultCode != RESULT_OK) {
                return null;
            }
            return intent.getData().toString();
        }
    }
}