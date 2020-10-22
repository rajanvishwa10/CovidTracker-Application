package com.example.covidtrackerapplication;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashSet;

public class CountryFragment extends Fragment {

    TextView textView, textView2, textView3, textView4;
    ProgressBar progressBar,progressBar2,progressBar3,progressBar4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_country, container, false);

        textView = view.findViewById(R.id.text);
        textView2 = view.findViewById(R.id.text2);
        textView3 = view.findViewById(R.id.text3);
        textView4 = view.findViewById(R.id.text4);

        progressBar = view.findViewById(R.id.progress);
        progressBar2 = view.findViewById(R.id.progress2);
        progressBar3 = view.findViewById(R.id.progress3);
        progressBar4 = view.findViewById(R.id.progress4);

        progressBar.setVisibility(View.VISIBLE);
        progressBar2.setVisibility(View.VISIBLE);
        progressBar3.setVisibility(View.VISIBLE);
        progressBar4.setVisibility(View.VISIBLE);

        getData();

        return view;

    }


    private void getData() {
        Bundle bundle = this.getArguments();
        String countryName = bundle.getString("countryName");
        String url = "https://api.covid19api.com/summary";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("Countries");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String country = jsonObject1.getString("Country");
                            String confirm = jsonObject1.getString("NewConfirmed");
                            String totalConfirm = jsonObject1.getString("TotalConfirmed");
                            String deaths = jsonObject1.getString("NewDeaths");
                            String NewRecovered = jsonObject1.getString("NewRecovered");

                            progressBar.setVisibility(View.INVISIBLE);
                            progressBar2.setVisibility(View.INVISIBLE);
                            progressBar3.setVisibility(View.INVISIBLE);
                            progressBar4.setVisibility(View.INVISIBLE);

                            Log.d("country", country.toString());

                            if (country.equals(countryName)) {
                                textView.setText(confirm);
                                textView2.setText(totalConfirm);
                                textView3.setText(NewRecovered);
                                textView4.setText(deaths);
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, error -> error.printStackTrace());
        RequestQueue requestQueue1 = Volley.newRequestQueue(getContext());
        requestQueue1.add(request);
    }

}