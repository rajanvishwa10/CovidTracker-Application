package com.example.covidtrackerapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;


import com.hbb20.CountryCodePicker;

public class MainActivity2 extends AppCompatActivity {
    Fragment fragment, fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));

        fragment = new NewFragment();
        fragment2 = new New2Fragment();

        CountryCodePicker countryCodePicker = findViewById(R.id.ccp);

        countryCodePicker.setOnCountryChangeListener(() -> {
            Bundle bundle = new Bundle();
            bundle.putString("country", countryCodePicker.getSelectedCountryName());
            fragment.setArguments(bundle);
            final FragmentManager fm = getSupportFragmentManager();
            final FragmentTransaction ft = fm.beginTransaction();
            ft.detach(fragment).attach(fragment).commit();
        });

        Bundle bundle = new Bundle();
        bundle.putString("country", countryCodePicker.getDefaultCountryName());

        fragment.setArguments(bundle);

        final FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, fragment);
        ft.commit();

    }

    public void onRadioButtonClicked(View view) {
        RadioButton radioButton = findViewById(R.id.radio);
        RadioButton radioButton2 = findViewById(R.id.radio2);

        boolean check = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radio:
                if (check) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment, fragment)
                            .addToBackStack(null)
                            .commit();
                }
                break;
            case R.id.radio2:
                if (check) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment, fragment2)
                            .addToBackStack(null)
                            .commit();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {

    }
}