package com.example.esieestyle.annonce_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.esieestyle.R;
import com.example.esieestyle.databinding.FragmentSelectionBinding;

public class SelectionFragment extends Fragment {

    private FragmentSelectionBinding binding;

    public SelectionFragment() {
        // Constructeur vide requis par Android
    }
    public static SelectionFragment newInstance() {
        SelectionFragment fragment = new SelectionFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSelectionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
