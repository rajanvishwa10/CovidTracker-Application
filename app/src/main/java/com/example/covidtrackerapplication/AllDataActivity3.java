package com.example.covidtrackerapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AllDataActivity3 extends AppCompatActivity {

    TextView newconfirmed, totalconfirmed, newdeaths, country,
            totaldeaths, newrecovered, totalrecovered, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_data3);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        newconfirmed = findViewById(R.id.newconfirmed);
        totalconfirmed = findViewById(R.id.totalconfirmed);
        newdeaths = findViewById(R.id.newdeath);
        totaldeaths = findViewById(R.id.totaldeath);
        newrecovered = findViewById(R.id.newrecovered);
        totalrecovered = findViewById(R.id.totalrecovered);
        date = findViewById(R.id.date);

        country = findViewById(R.id.country);

        newconfirmed.setText(": " + getIntent().getStringExtra("newconfirmed"));
        totalconfirmed.setText(": " + getIntent().getStringExtra("totalconfirmed"));
        newdeaths.setText(": " + getIntent().getStringExtra("newdeaths"));
        totaldeaths.setText(": " + getIntent().getStringExtra("totaldeaths"));
        newrecovered.setText(": " + getIntent().getStringExtra("newrecovered"));
        totalrecovered.setText(": " + getIntent().getStringExtra("totalrecovered"));
        country.setText(getIntent().getStringExtra("country"));

        String date2 = getIntent().getStringExtra("date");
        try {
            String[] split = date2.split("T");
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat outputFormat = new SimpleDateFormat("dd, MMM yy");
            String inputDateStr = split[0];
            Date date3 = inputFormat.parse(inputDateStr);
            String outputDateStr = outputFormat.format(date3);
            DateFormat inputFormat2 = new SimpleDateFormat("HH:mm:ss");
            DateFormat outputFormat2 = new SimpleDateFormat("hh:mm:ss");
            String inputDateStr2 = split[1];
            Date date4 = inputFormat2.parse(inputDateStr2);
            String outputDateStr2 = outputFormat2.format(date4);
            date.setText("Last Updated : " + outputDateStr + " " + outputDateStr2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}