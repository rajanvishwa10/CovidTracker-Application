package com.example.covidtrackerapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class NewFragment extends Fragment {
    Fragment fragment, fragment2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new, container, false);

        fragment = new CountryFragment();
        fragment2 = new WorldWideFragment();

        final FragmentManager fm = getActivity().getSupportFragmentManager();
        final FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment2, fragment);
        ft.commit();


        Bundle bundle = this.getArguments();
        String countryName = bundle.getString("country");

        Bundle bundle2 = new Bundle();
        bundle2.putString("countryName", countryName);
        fragment.setArguments(bundle2);

        RadioGroup radioGroup = (RadioGroup) view .findViewById(R.id.radioGroup2);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            switch(checkedId) {
                case R.id.radio3:
                    fragment.setArguments(bundle2);
                    getActivity().getSupportFragmentManager().beginTransaction().remove(fragment2)
                            .replace(R.id.fragment2,fragment)
                            .addToBackStack(null)
                            .commit();
                    break;
                case R.id.radio4:
                    getActivity().getSupportFragmentManager().beginTransaction().remove(fragment)
                            .replace(R.id.fragment2,fragment2)
                            .addToBackStack(null)
                            .commit();
                    break;
            }
        });

        return view;
    }


}