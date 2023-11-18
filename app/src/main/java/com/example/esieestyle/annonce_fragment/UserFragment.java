package com.example.esieestyle.annonce_fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.esieestyle.ConnectionActivity;
import com.example.esieestyle.databinding.FragmentUserBinding;
import com.google.firebase.auth.FirebaseAuth;

public class UserFragment extends Fragment {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FragmentUserBinding binding;

    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
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
        binding = FragmentUserBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.deconnecteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDeconnectionDialogWindows();
            }
        });

        binding.informationsUserEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void displayDeconnectionDialogWindows() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Vous allez vous déconnecter");
        builder.setMessage("Voulez-vous vraiment vous déconnecter ?");
        builder.setPositiveButton("Déconnecter", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                firebaseAuth.signOut();
                Intent intent = new Intent(getActivity(), ConnectionActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}