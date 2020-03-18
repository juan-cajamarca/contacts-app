package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingButton;
    ListView contactsListView;
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

        ContactAdapter adapter = new ContactAdapter(this, R.layout.listview_item, contacts);

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
                break;
            case R.id.invertListItem:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}
