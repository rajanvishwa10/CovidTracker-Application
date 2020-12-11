package com.example.covidtrackerapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hbb20.CountryCodePicker;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {
    Fragment fragment, fragment2;
    TextView textView, textView2;
    String country;
    CountryCodePicker countryCodePicker;
    ImageButton imageButton;
    Animation topAnim, leftAnim, rightAnim;
    ImageView imageView;
    RadioGroup radioGroup;
    RadioButton radioButton, radioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.black));

        SwitchCompat switchCompat = findViewById(R.id.switchCompat);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    getWindow().setStatusBarColor(getResources().getColor(R.color.black));
                    getWindow().setNavigationBarColor(getResources().getColor(R.color.black));
                } else {
                    getWindow().getDecorView().setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
                    getWindow().setStatusBarColor(getResources().getColor(R.color.white));
                    getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                }
            }
        });

        fragment = new NewFragment();
        fragment2 = new New2Fragment();

        textView = findViewById(R.id.date);
        textView2 = findViewById(R.id.textView4);

        countryCodePicker = findViewById(R.id.ccp);
        country = countryCodePicker.getDefaultCountryName();

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        rightAnim = AnimationUtils.loadAnimation(this, R.anim.right_animation);
        leftAnim = AnimationUtils.loadAnimation(this, R.anim.left_animation);

        imageView = findViewById(R.id.imageView2);
        imageView.setAnimation(topAnim);

        imageButton = findViewById(R.id.imageButton);
        imageButton.setAnimation(leftAnim);


        textView.setAnimation(leftAnim);
        textView2.setAnimation(rightAnim);

        radioButton = findViewById(R.id.radio);
        radioButton2 = findViewById(R.id.radio2);

        radioButton.setAnimation(leftAnim);
        radioButton2.setAnimation(rightAnim);

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setAnimation(topAnim);

        countryCodePicker.setAnimation(rightAnim);

        countryCodePicker.setOnCountryChangeListener(() -> {
            Bundle bundle = new Bundle();
            bundle.putString("country", countryCodePicker.getSelectedCountryName());
            fragment.setArguments(bundle);
            final FragmentManager fm = getSupportFragmentManager();
            final FragmentTransaction ft = fm.beginTransaction();
            ft.detach(fragment).attach(fragment).commit();
            country = countryCodePicker.getSelectedCountryName();
        });

        Bundle bundle = new Bundle();
        bundle.putString("country", countryCodePicker.getDefaultCountryName());

        fragment.setArguments(bundle);

        final FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, fragment);
        ft.commit();

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            switch (checkedId) {
                case R.id.radio:
                    getSupportFragmentManager().beginTransaction().remove(fragment2)
                            .replace(R.id.fragment, fragment)
                            .addToBackStack(null)
                            .commit();
                    break;
                case R.id.radio2:
                    getSupportFragmentManager().beginTransaction().remove(fragment)
                            .replace(R.id.fragment, fragment2)
                            .addToBackStack(null)
                            .commit();
                    break;
            }
        });

        getData();
    }


    @Override
    public void onBackPressed() {

    }

    private void getData() {
        String url = "https://api.covid19api.com/summary";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("Countries");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String date = jsonObject1.getString("Date");
                            String countryName = jsonObject1.getString("Country");

                            String[] split = date.split("T");
                            String split2 = split[1].substring(0, split[1].length() - 1);

                            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                            DateFormat outputFormat = new SimpleDateFormat("dd, MMM YY");
                            String inputDateStr = split[0];
                            Date date2 = inputFormat.parse(inputDateStr);
                            String outputDateStr = outputFormat.format(date2);

                            if (countryName.equals(country)) {
                                textView.setText("Last Updated : " + outputDateStr + " " + split2);
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, error -> error.printStackTrace());
        RequestQueue requestQueue1 = Volley.newRequestQueue(this);
        requestQueue1.add(request);
    }

    public void website(View view) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse("https://www.who.int/emergencies/diseases/novel-coronavirus-2019/advice-for-public"));
    }
}