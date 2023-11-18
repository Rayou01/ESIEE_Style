package com.example.esieestyle.annonce_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.esieestyle.R;
import com.example.esieestyle.adapter.AnnonceAdapter;
import com.example.esieestyle.databinding.FragmentHomeBinding;
import com.example.esieestyle.model.Annonce;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class HomeFragment extends Fragment{

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FragmentHomeBinding binding;
    private AnnonceAdapter adapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        Query query = firebaseFirestore.collection("Annonces").orderBy("productName", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Annonce> options = new FirestoreRecyclerOptions.Builder<Annonce>()
                .setQuery(query, Annonce.class)
                .build();

        adapter = new AnnonceAdapter(options);
        binding.recylcerViewAnnonce.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recylcerViewAnnonce.setAdapter(adapter);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.includeTopBarHome.topToolbar.getMenu().clear();
        binding.includeTopBarHome.topToolbar.setTitle("Home");
        binding.includeTopBarHome.topToolbar.setLogo(R.drawable.baseline_home);
        binding.includeTopBarHome.topToolbar.inflateMenu(R.menu.home_menu);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}