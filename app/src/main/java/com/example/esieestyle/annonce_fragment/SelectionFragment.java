package com.example.esieestyle.annonce_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.esieestyle.R;
import com.example.esieestyle.databinding.FragmentSelectionBinding;
import com.example.esieestyle.model.Annonce;
import com.example.esieestyle.utils.FirestoreUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class SelectionFragment extends Fragment {

    private FragmentSelectionBinding binding;

    public SelectionFragment() {
        // Constructeur vide requis par Android
    }
    public static SelectionFragment newInstance() {
        return new SelectionFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSelectionBinding.inflate(inflater, container, false);

        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setVisibility(View.GONE);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            Annonce annonce = Annonce.fromBundle(bundle);
            binding.textViewTitle.setText(annonce.getProductName());
            binding.sellerName.setText(annonce.getSellerName());
        }
        FirestoreUtils.getCollectionRef("Users").document(Objects.requireNonNull(FirestoreUtils.getUserID()))
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    String userMail = documentSnapshot.getString("userMail");
                    binding.sellerEmail.setText(userMail);
                });
    }
}
