package com.example.esieestyle.annonce_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.esieestyle.ConnectionActivity;
import com.example.esieestyle.adapter.AnnonceAdapter;
import com.example.esieestyle.databinding.FragmentUserBinding;
import com.example.esieestyle.model.Annonce;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class UserFragment extends Fragment {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FragmentUserBinding binding;
    private AnnonceAdapter adapter;
    String userID;

    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null){
            userID = firebaseUser.getUid();
            Query query = firebaseFirestore.collection("Users")
                    .document(userID)
                    .collection("Annonces ajoutées")
                    .orderBy("productPrice", Query.Direction.ASCENDING);
            FirestoreRecyclerOptions<Annonce> options = new FirestoreRecyclerOptions.Builder<Annonce>()
                    .setQuery(query, Annonce.class)
                    .build();

            adapter = new AnnonceAdapter(options);
            binding.recyclerViewAnnoncesUser.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.recyclerViewAnnoncesUser.setAdapter(adapter);
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.deconnecteButton.setOnClickListener(view1 -> displayDeconnectionDialogWindows());
    }

    private void displayDeconnectionDialogWindows() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        builder.setTitle("Vous allez vous déconnecter");
        builder.setMessage("Voulez-vous vraiment vous déconnecter ?");
        builder.setPositiveButton("Déconnecter", (dialog, id) -> {
            firebaseAuth.signOut();
            Intent intent = new Intent(getActivity(), ConnectionActivity.class);
            startActivity(intent);
        });
        AlertDialog dialog = builder.create();
        dialog.show();
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