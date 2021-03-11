package com.example.covidtrackerapplication;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {
    private final Context context;
    private final String[] hospitalList;

    public HospitalAdapter(Context context, String[] hospitalList) {
        this.context = context;
        this.hospitalList = hospitalList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hospitallayout, parent, false);
        return new HospitalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = hospitalList[position];
        if(name.equals("Delhi") || name.equals("Mumbai") || name.equals("Bangalore") || name.equals("Chandigarh") ||
                name.equals("Pune") || name.equals("Hyderabad") || name.equals("Kerala") || name.equals("Assam")
                || name.equals("Chennai") || name.equals("Bengaluru")) {
            holder.textView.setText(Html.fromHtml("<b>"+name+"</b>"));
            holder.textView.setTextSize(20);
            holder.textView.setTextColor(Color.BLACK);
            holder.textView.setPadding(5,25,5,5);
        }else if(name.equals("Government hospitals") ||
                name.equals("Government centres")||
                name.equals("Government vaccination centres")
        ||name.equals("Private hospitals") || name.equals("Private centres")){
            holder.textView.setText(name);
            holder.textView.setTextSize(18);
            holder.textView.setPadding(5,15,5,15);
        }
        else{
            holder.textView.setTextSize(15);
            holder.textView.setText(name);
            holder.textView.setPadding(20,5,5,5);
        }

    }

    @Override
    public int getItemCount() {
        return hospitalList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
