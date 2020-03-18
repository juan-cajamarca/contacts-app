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

        // Getting data from AddContact Activity
        Intent intent = getIntent();
        ArrayList<Contact> contactsFromAddContactActivity = intent.getParcelableArrayListExtra("contacts");

        // If no data, set a default list
        if (contactsFromAddContactActivity != null && contactsFromAddContactActivity.size() > 0) {
            contacts = contactsFromAddContactActivity;
        } else {
            contacts = new ArrayList<Contact>();
            contacts.add(new Contact("Xiomara Acuña", "546 546 4646", "Familia"));
            contacts.add(new Contact("Miguel Ruiz", "546 546 4646", "Ocasional"));
            contacts.add(new Contact("Pedro Arias", "300 112 2222", "Amigo"));
            contacts.add(new Contact("Fernando Vargas", "546 546 4646", "Trabajo"));
            contacts.add(new Contact("Luisa Gómez", "300 112 2222", "Amigo"));
            contacts.add(new Contact("Jose Ruiz", "546 546 4646", "Ocasional"));
            contacts.add(new Contact("Adrían Acosta", "546 546 4646", "Amigo"));
            contacts.add(new Contact("Juan Cajamarca", "300 000 0000", "Ocasional"));
            contacts.add(new Contact("Fabián Ramos", "546 546 4646", "Trabajo"));
        }

        adapter = new ContactAdapter(this, R.layout.listview_item, contacts);

        contactsListView = (ListView) findViewById(R.id.contactsListView);
        contactsListView.setAdapter(adapter);

        // Configuring floating button
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
                for (int i = 0; i < contacts.size(); i++) {
                    for (int j = i + 1; j < contacts.size(); j++) {
                        if (contacts.get(i).getName().compareTo(contacts.get(j).getName()) > 0) {
                            Contact temp = contacts.get(i);
                            Contact aux = contacts.get(j);
                            contacts.remove(temp);
                            contacts.remove(aux);
                            contacts.add(i, aux);
                            contacts.add(j, temp);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.sortByGroupItem:
                for (int i = 0; i < contacts.size(); i++) {
                    for (int j = i + 1; j < contacts.size(); j++) {
                        if (contacts.get(i).getGroup().compareTo(contacts.get(j).getGroup()) > 0) {
                            Contact temp = contacts.get(i);
                            Contact aux = contacts.get(j);
                            contacts.remove(temp);
                            contacts.remove(aux);
                            contacts.add(i, aux);
                            contacts.add(j, temp);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
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
