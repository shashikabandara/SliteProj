package com.example.sliteproj;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView mRecyclerView;
    private DatabaseHelper DatabaseHelper;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setTitle("All Appointment and Payment Details");

        fab = findViewById(R.id.addFabButton);
        mRecyclerView = findViewById(R.id.recyclerView);


        DatabaseHelper = new DatabaseHelper(this);

        showRecord();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddRecordActivity.class);
                intent.putExtra("editMode", false);
                startActivity(intent);
            }
        });
    }

    private void showRecord() {

        Adapter Adapter = new Adapter(MainActivity.this,
                DatabaseHelper.getAllData(Constants.C_Add_TIMESTAMP + " DESC"));

        mRecyclerView.setAdapter(Adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showRecord();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }

        return super.onKeyDown(keyCode, event);
    }
}
