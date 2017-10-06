package com.adibta.android_sqllite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.adibta.android_sqllite.adapter.NoteListAdapter;
import com.adibta.android_sqllite.data.NoteContract;
import com.adibta.android_sqllite.data.NoteDbHelper;
import com.adibta.android_sqllite.model.Note_Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private NoteDbHelper mDbHelper;
    private ArrayList<Note_Item> noteDetailsList;
    private RecyclerView recyclerView;
    private NoteListAdapter noteListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddActivity.class));
            }
        });
        //initialized db
        mDbHelper = new NoteDbHelper(this);


        // Get all the Android Versions data from db
        noteDetailsList = mDbHelper.getNote_Items();


        recyclerView = (RecyclerView) findViewById(R.id.recycler_list);
        noteListAdapter = new NoteListAdapter(getApplicationContext(), noteDetailsList);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(noteListAdapter);
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
        if (id == R.id.action_clear) {

            clearAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //this method will clear all the records on the Note table
    public void clearAll() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete(NoteContract.Note.TABLE_NAME, null, null);
        db.close();
        noteListAdapter.clear();
        noteListAdapter.notifyDataSetChanged();

    }

}
