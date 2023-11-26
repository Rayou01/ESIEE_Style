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
import com.example.esieestyle.R;
import com.example.esieestyle.RecyclerViewInterface;
import com.example.esieestyle.adapter.AnnonceAdapter;
import com.example.esieestyle.databinding.FragmentUserBinding;
import com.example.esieestyle.model.Annonce;
import com.example.esieestyle.utils.FirestoreUtils;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import java.util.Objects;

public class UserFragment extends Fragment implements RecyclerViewInterface {

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

        userID = FirestoreUtils.getUserID();
        Query query = FirestoreUtils.getAjoutUserCollectionRef()
                .orderBy("productPrice", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Annonce> options = new FirestoreRecyclerOptions.Builder<Annonce>()
                .setQuery(query, Annonce.class)
                .build();

        adapter = new AnnonceAdapter(options, this);
        binding.recyclerViewAnnoncesUser.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerViewAnnoncesUser.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.deconnecteButton.setOnClickListener(view1 -> displayDeconnectionDialogWindows());

        FirestoreUtils.getCollectionRef("Users").document(Objects.requireNonNull(FirestoreUtils.getUserID())).get()
                .addOnSuccessListener(documentSnapshot -> {
                    String userName = documentSnapshot.getString("userName") + " " + documentSnapshot.getString("userSurname");
                    binding.nomPrenomUser.setText(userName);
                })
                .addOnFailureListener(e -> binding.nomPrenomUser.setText(R.string.nom_prenom));

        binding.includeTopBarUser.topToolbar.getMenu().clear();
        binding.includeTopBarUser.topToolbar.setTitle("Mon Compte");
        binding.includeTopBarUser.topToolbar.setLogo(R.drawable.baseline_person);
    }


    private void displayDeconnectionDialogWindows() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        builder.setTitle("Vous allez vous déconnecter");
        builder.setMessage("Voulez-vous vraiment vous déconnecter ?");
        builder.setPositiveButton("Déconnecter", (dialog, id) -> {
            FirestoreUtils.signOut();
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

    @Override
    public void OnItemClick(DocumentSnapshot documentSnapshot, int position) {}
}