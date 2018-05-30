package com.example.pro.driveassister;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CallLogActivity extends ListActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);

        listView = (ListView) findViewById(R.id.list);
        String[] values = new String[] { "Android Example ListActivity", "Adapter implementation", "Simple List View With ListActivity",
                "ListActivity Android", "Android Example", "ListActivity Source Code", "ListView ListActivity Array Adapter", "Android Example ListActivity" };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);


        // Assign adapter to List
        setListAdapter(adapter);


    }

}
