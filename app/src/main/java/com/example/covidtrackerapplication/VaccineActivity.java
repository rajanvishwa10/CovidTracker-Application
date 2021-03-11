package com.example.covidtrackerapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.tuann.floatingactionbuttonexpandable.FloatingActionButtonExpandable;

import java.util.ArrayList;
import java.util.List;

public class VaccineActivity extends AppCompatActivity {

    String[] hospital;
    RecyclerView recyclerView;
    HospitalAdapter hospitalAdapter;
    ExtendedFloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));

        hospital = getResources().getStringArray(R.array.hospitalName);

        fab = findViewById(R.id.fab);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        hospitalAdapter = new HospitalAdapter(getApplicationContext(), hospital);
        recyclerView.setAdapter(hospitalAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent();
            }
        });
    }

    private void intent(){
        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        intent();
    }

    public void registerWebsite(View view) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse("https://selfregistration.cowin.gov.in/"));
    }
}