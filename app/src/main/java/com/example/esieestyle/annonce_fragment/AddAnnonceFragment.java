package com.example.esieestyle.annonce_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.esieestyle.R;
import com.example.esieestyle.databinding.FragmentAddAnnonceBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddAnnonceFragment extends Fragment {

    private FragmentAddAnnonceBinding binding;

    public AddAnnonceFragment() {
        // Required empty public constructor
    }

    public static AddAnnonceFragment newInstance() {
        AddAnnonceFragment fragment = new AddAnnonceFragment();
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
        binding = FragmentAddAnnonceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}