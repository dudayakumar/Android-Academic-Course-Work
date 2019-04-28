package com.example.bloodbank3.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bloodbank3.R;

public class Home extends Fragment {
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home, container, false);
        getActivity().setTitle("Home page");

        textView = view.findViewById(R.id.homeText);
        //Linkify.addLinks(textView, Linkify.ALL);
        return view;
    }
}
