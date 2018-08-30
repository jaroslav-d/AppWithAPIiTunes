package com.example.jaroslav.taskfromforasoft;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        button = findViewById(R.id.button);
        LinearLayout mainLinearLayout = findViewById(R.id.mainLinearLayout);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mainLinearLayout.setOrientation(LinearLayout.VERTICAL);
        } else {
            mainLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        }
        button.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,AlbumListActivity.class);
            intent.putExtra("firstName",firstName.getText());
            intent.putExtra("lastName",lastName.getText());
            startActivity(intent);
        }
    };
}
