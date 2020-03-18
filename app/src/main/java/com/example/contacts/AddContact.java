package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddContact extends AppCompatActivity implements View.OnClickListener {

    EditText contactName;
    EditText contactPhone;
    Spinner groupSpinner;
    Button cancelButton;
    Button saveButton;

    ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        // Getting edit texts and spinner from graphic view
        contactName = (EditText) findViewById(R.id.contactName);
        contactPhone = (EditText) findViewById(R.id.contactPhone);
        groupSpinner = (Spinner) findViewById(R.id.groupSpinner);

        // Getting buttons from graphic view
        cancelButton = (Button) findViewById(R.id.cancelButton);
        saveButton = (Button) findViewById(R.id.saveContactButton);

        // Setting this class click listener to buttons
        cancelButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);

        // Adding spinner options
        List spinnerList = new ArrayList<String>();
        spinnerList.add("Familia");
        spinnerList.add("Trabajo");
        spinnerList.add("Amigo");
        spinnerList.add("Ocasional");

        // Setting array adapter for spinner
        ArrayAdapter spinnerListAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, spinnerList);
        spinnerListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupSpinner.setAdapter(spinnerListAdapter);

        // Getting contacts from main activity
        Intent intent = getIntent();
        ArrayList<Contact> contactsFromMainActivity = intent.getParcelableArrayListExtra("contacts");

        // Verify if there is one or more contacts
        if (contactsFromMainActivity != null && contactsFromMainActivity.size() > 0) {
            contacts = contactsFromMainActivity;
        } else {
            contacts = new ArrayList<Contact>();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_contact_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.name_in_capital_letters_menu_item:
                if (contactName.getText().toString().trim().isEmpty()) {
                    Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
                } else {
                    contactName.setText(contactName.getText().toString().toUpperCase());
                }
                break;
            case R.id.generate_random_phone_number:
                String phoneNumber = chooseRandomPhonePrefix();
                for (int i = 0; i < 7; i++) {
                    if (i == 0 || i == 3) {
                        phoneNumber += " ";
                    }
                    int randomNumber = generateRandomNumber();
                    phoneNumber += randomNumber;
                }
                contactPhone.setText(phoneNumber);
                break;
            case R.id.clear_data_menu_item:
                contactName.setText("");
                contactPhone.setText("");
                groupSpinner.setSelection(0, true);
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveContactButton:
                saveContact();
                break;
            case R.id.cancelButton:
                finish();
                break;
            default:
                break;
        }
    }

    private void saveContact() {
        if (!contactName.getText().toString().trim().isEmpty() && !contactPhone.getText().toString().trim().isEmpty()) {
            // New contact variable created
            Contact newContact;

            // Getting new contact data
            String name = contactName.getText().toString().trim();
            String phone = contactPhone.getText().toString().trim();
            String group = groupSpinner.getSelectedItem().toString();

            // Creating the contact object
            newContact = new Contact(name, phone, group);

            // Adding the new object to array list
            contacts.add(newContact);

            // Getting back to main activity with new contacts list
            Intent intent = new Intent(this, MainActivity.class);
            intent.putParcelableArrayListExtra("contacts", contacts);
            startActivity(intent);
        } else {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_LONG).show();
        }
    }

    private String chooseRandomPhonePrefix() {
        int min = 0;
        int max = 2;
        int random = (int) (Math.random() * (max - min + 1) + min);

        switch (random) {
            case 0:
                return "300";
            case 1:
                return "310";
            case 2:
                return "320";
            default:
                return "301";
        }
    }

    private int generateRandomNumber() {
        int min = 0;
        int max = 9;

        return (int) (Math.random() * (max - min + 1) + min);
    }
}
