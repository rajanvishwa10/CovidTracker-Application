package com.example.covidtrackerapplication;

import android.net.Uri;
import android.os.Bundle;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class New2Fragment extends Fragment {

    Animation topAnim, leftAnim, rightAnim;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new2, container, false);

        topAnim = AnimationUtils.loadAnimation(getContext(), R.anim.top_animation);
        rightAnim = AnimationUtils.loadAnimation(getContext(), R.anim.right_animation);
        leftAnim = AnimationUtils.loadAnimation(getContext(), R.anim.left_animation);

        TextView textView = view.findViewById(R.id.noMoreText);
        TextView textView2 = view.findViewById(R.id.noMoreText2);
        TextView textView3 = view.findViewById(R.id.prevention);

        textView.setAnimation(topAnim);
        textView2.setAnimation(topAnim);
        textView3.setAnimation(topAnim);

        textView.setOnClickListener(view1 -> {
            customtabs("https://rb.gy/cp0mwc");
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customtabs("https://rb.gy/b9fxwe");
            }
        });

        CardView cardView = view.findViewById(R.id.cardView);
        CardView cardView2 = view.findViewById(R.id.cardView2);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customtabs("https://www.who.int/images/default-source/health-topics/coronavirus/clothing-masks-infographic---(web)-logo-who.png?sfvrsn=b15e3742_16");
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customtabs("https://www.who.int/gpsc/5may/How_To_HandWash_Poster.pdf");
            }
        });
        return view;
    }
    private void customtabs(String url){
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(getContext(), Uri.parse(url));
    }
}