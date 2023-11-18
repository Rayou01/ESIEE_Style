package com.example.esieestyle.annonce_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.esieestyle.R;
import com.example.esieestyle.databinding.FragmentFavorisBinding;

public class FavorisFragment extends Fragment {

    private FragmentFavorisBinding binding;

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
        binding = FragmentFavorisBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.includeTopBarFavoris.topToolbar.getMenu().clear();
        binding.includeTopBarFavoris.topToolbar.setTitle("Favoris");
        binding.includeTopBarFavoris.topToolbar.setLogo(R.drawable.baseline_favorite);
    }
}