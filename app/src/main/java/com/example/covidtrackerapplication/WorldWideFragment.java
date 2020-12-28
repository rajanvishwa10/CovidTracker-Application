package com.example.covidtrackerapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
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
    ProgressBar progressBar, progressBar2, progressBar3, progressBar4;
    CardView cardView, cardView2, cardView3, cardView4;
    Animation topAnim, bottomAnim;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_world_wide, container, false);

        textView = view.findViewById(R.id.text);
        textView2 = view.findViewById(R.id.text2);
        textView3 = view.findViewById(R.id.text3);
        textView4 = view.findViewById(R.id.text4);

        progressBar = view.findViewById(R.id.progress5);
        progressBar2 = view.findViewById(R.id.progress6);
        progressBar3 = view.findViewById(R.id.progress7);
        progressBar4 = view.findViewById(R.id.progress8);

        progressBar.setVisibility(View.VISIBLE);
        progressBar2.setVisibility(View.VISIBLE);
        progressBar3.setVisibility(View.VISIBLE);
        progressBar4.setVisibility(View.VISIBLE);

        cardView = view.findViewById(R.id.cardView);
        cardView2 = view.findViewById(R.id.cardView2);
        cardView3 = view.findViewById(R.id.cardView3);
        cardView4 = view.findViewById(R.id.cardView4);

        topAnim = AnimationUtils.loadAnimation(getContext(),R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(getContext(),R.anim.bottom_animation);

        cardView.setAnimation(topAnim);
        cardView2.setAnimation(topAnim);

        cardView3.setAnimation(bottomAnim);
        cardView4.setAnimation(bottomAnim);

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
                            String totaldeaths = jsonObject1.getString("TotalDeaths");
                            String NewRecovered = jsonObject1.getString("NewRecovered");
                            String totalRecovered = jsonObject1.getString("TotalRecovered");
                            String date = jsonObject.getString("Date");

                            progressBar.setVisibility(View.INVISIBLE);
                            progressBar2.setVisibility(View.INVISIBLE);
                            progressBar3.setVisibility(View.INVISIBLE);
                            progressBar4.setVisibility(View.INVISIBLE);

                            textView.setText(confirm);
                            textView2.setText(totalConfirm);
                            textView3.setText(NewRecovered);
                            textView4.setText(deaths);
                            cardView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getContext(), AllDataActivity3.class);
                                    intent.putExtra("newconfirmed", confirm);
                                    intent.putExtra("totalconfirmed", totalConfirm);
                                    intent.putExtra("newdeaths", deaths);
                                    intent.putExtra("totaldeaths", totaldeaths);
                                    intent.putExtra("newrecovered", NewRecovered);
                                    intent.putExtra("totalrecovered", totalRecovered);
                                    intent.putExtra("country", "WorldWide");
                                    intent.putExtra("date", date);
                                    startActivity(intent);
                                }
                            });
                            cardView2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getContext(), AllDataActivity3.class);
                                    intent.putExtra("newconfirmed", confirm);
                                    intent.putExtra("totalconfirmed", totalConfirm);
                                    intent.putExtra("newdeaths", deaths);
                                    intent.putExtra("totaldeaths", totaldeaths);
                                    intent.putExtra("newrecovered", NewRecovered);
                                    intent.putExtra("totalrecovered", totalRecovered);
                                    intent.putExtra("country", "WorldWide");
                                    intent.putExtra("date", date);
                                    startActivity(intent);
                                }
                            });
                            cardView3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getContext(), AllDataActivity3.class);
                                    intent.putExtra("newconfirmed", confirm);
                                    intent.putExtra("totalconfirmed", totalConfirm);
                                    intent.putExtra("newdeaths", deaths);
                                    intent.putExtra("totaldeaths", totaldeaths);
                                    intent.putExtra("newrecovered", NewRecovered);
                                    intent.putExtra("totalrecovered", totalRecovered);
                                    intent.putExtra("country", "WorldWide");
                                    intent.putExtra("date", date);
                                    startActivity(intent);
                                }
                            });
                            cardView4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getContext(), AllDataActivity3.class);
                                    intent.putExtra("newconfirmed", confirm);
                                    intent.putExtra("totalconfirmed", totalConfirm);
                                    intent.putExtra("newdeaths", deaths);
                                    intent.putExtra("totaldeaths", totaldeaths);
                                    intent.putExtra("newrecovered", NewRecovered);
                                    intent.putExtra("totalrecovered", totalRecovered);
                                    intent.putExtra("country", "WorldWide");
                                    intent.putExtra("date", date);
                                    startActivity(intent);
                                }
                            });
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