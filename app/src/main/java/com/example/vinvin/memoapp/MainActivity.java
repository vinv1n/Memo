package com.example.vinvin.memoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String> task_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SetupListview();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LaunchActivity();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void SetupListview() {
        task_list = new ArrayList<>();
        listView = (ListView) findViewById(R.id.new_task);
        adapter = new ArrayAdapter(this, R.layout.activity_listview, task_list);
        listView.setAdapter(adapter);
    }

    private void UpdateListview(String new_task) {
        task_list.add(new_task);
        adapter.notifyDataSetChanged();
    }

    private void LaunchActivity() {
        Intent intent = new Intent(MainActivity.this.getApplicationContext(), EditTask.class);
        intent.putExtra("Tasks", task_list);
        startActivityForResult(intent, 1337);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1337) {
            if (data != null) {
                String [] new_task = data.getStringArrayExtra("Updated_tasks");
                Toast.makeText(getApplicationContext(), new_task.length, Toast.LENGTH_LONG).show();
            }
        }
    }
}
