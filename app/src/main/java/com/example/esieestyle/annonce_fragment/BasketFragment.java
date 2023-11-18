package com.example.esieestyle.annonce_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.esieestyle.R;
import com.example.esieestyle.databinding.FragmentBasketBinding;

public class BasketFragment extends Fragment {

    private FragmentBasketBinding binding;

    public BasketFragment() {
        // Required empty public constructor
    }

    public static BasketFragment newInstance() {
        BasketFragment fragment = new BasketFragment();
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
        binding = FragmentBasketBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.includeTopBarBasket.topToolbar.getMenu().clear();
        binding.includeTopBarBasket.topToolbar.setTitle("Panier");
        binding.includeTopBarBasket.topToolbar.setLogo(R.drawable.baseline_shopping_cart);
    }
}