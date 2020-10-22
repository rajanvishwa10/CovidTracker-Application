package com.example.covidtrackerapplication;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class WorldWideFragment extends Fragment {

    TextView textView, textView2, textView3, textView4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_world_wide, container, false);

        textView = view.findViewById(R.id.text);
        textView2 = view.findViewById(R.id.text2);
        textView3 = view.findViewById(R.id.text3);
        textView4 = view.findViewById(R.id.text4);

        getData();

        return view;
    }

    private void getData() {
        String url = "https://api.covid19api.com/summary";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("Global");
                            String confirm = jsonObject1.getString("NewConfirmed");
                            String totalConfirm = jsonObject1.getString("TotalConfirmed");
                            String deaths = jsonObject1.getString("NewDeaths");
                            String NewRecovered = jsonObject1.getString("NewRecovered");

                            textView.setText(confirm);
                            textView2.setText(totalConfirm);
                            textView3.setText(NewRecovered);
                            textView4.setText(deaths);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        RequestQueue requestQueue1 = Volley.newRequestQueue(getContext());
        requestQueue1.add(request);
    }
}