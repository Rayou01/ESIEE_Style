package com.example.esieestyle.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.esieestyle.R;

public class FavorisFragment extends Fragment {

    public FavorisFragment() {
        // Required empty public constructor
    }

    public static FavorisFragment newInstance() {
        FavorisFragment fragment = new FavorisFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoris, container, false);
    }
}