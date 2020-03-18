package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingButton;
    ListView contactsListView;
    ContactAdapter adapter;
    ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        ArrayList<Contact> contactsFromAddContactActivity = intent.getParcelableArrayListExtra("contacts");

        if (contactsFromAddContactActivity != null && contactsFromAddContactActivity.size() > 0) {
            contacts = contactsFromAddContactActivity;
        } else {
            contacts = new ArrayList<Contact>();
        }

        adapter = new ContactAdapter(this, R.layout.listview_item, contacts);

        contactsListView = (ListView) findViewById(R.id.contactsListView);
        contactsListView.setAdapter(adapter);

        floatingButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContact.class);
                intent.putParcelableArrayListExtra("contacts", contacts);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sortByLastNameItem:
                break;
            case R.id.sortByGroupItem:
                break;
            case R.id.deleteAllItem:
                contacts.removeAll(contacts);
                adapter.notifyDataSetChanged();
                break;
            case R.id.invertListItem:
                Collections.reverse(contacts);
                adapter.notifyDataSetChanged();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}
