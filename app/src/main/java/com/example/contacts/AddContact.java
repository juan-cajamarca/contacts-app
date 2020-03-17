package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AddContact extends AppCompatActivity {

    Spinner groupSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        groupSpinner = (Spinner) findViewById(R.id.groupSpinner);
        List spinnerList = new ArrayList<String>();
        spinnerList.add("Familia");
        spinnerList.add("Trabajo");
        spinnerList.add("Amigo");
        spinnerList.add("Ocasional");

        ArrayAdapter spinnerListAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, spinnerList);
        spinnerListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupSpinner.setAdapter(spinnerListAdapter);
    }
}
