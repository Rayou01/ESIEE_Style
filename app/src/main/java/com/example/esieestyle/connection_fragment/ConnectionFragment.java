package com.example.esieestyle.connection_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.esieestyle.AnnonceActivity;
import com.example.esieestyle.R;
import com.example.esieestyle.databinding.FragmentConnectionBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ConnectionFragment extends Fragment {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private FragmentConnectionBinding binding;
    private boolean is_User_Text_Empty, is_Password_Text_Empty;

    public static ConnectionFragment newInstance() {
        ConnectionFragment fragment = new ConnectionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentConnectionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //When the UI is created, we disable the connection button until the user enter something
        binding.connectionButton.setEnabled(false);
        firebaseAuth.signOut();
        //Toast.makeText(getContext(), "Veuillez vous connecter", Toast.LENGTH_LONG).show();

        is_User_Text_Empty = true;
        is_Password_Text_Empty = true;

        binding.userMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //We look if the user has enter a text for the user name EditText
                is_User_Text_Empty = editable.toString().isEmpty();
                binding.connectionButton.setEnabled(!(is_Password_Text_Empty||is_User_Text_Empty));
            }
        });

        binding.userPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //We look if the user has enter a text for the user password EditText
                is_Password_Text_Empty = editable.toString().isEmpty();
                //Then, we test if both of EditText has been modified and enable the button if it's the case
                binding.connectionButton.setEnabled(!(is_Password_Text_Empty||is_User_Text_Empty));
            }
        });


        binding.registerButton.setOnClickListener(view1 -> {
            //Naviguer vers le fragment suivant
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            RegisterFragment registerFragment = new RegisterFragment();
            fragmentTransaction.replace(R.id.fragment_container_view_id_Main, registerFragment);
            fragmentTransaction.commit();
        });

        binding.connectionButton.setOnClickListener(view12 -> {
            String email, password;
            email = String.valueOf(binding.userMail.getText());
            password = String.valueOf(binding.userPassword.getText());

            if(TextUtils.isEmpty(email)) {
                Toast.makeText(getContext(), "Entrer un mail", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password)) {
                Toast.makeText(getContext(), "Entrer un mot de passe", Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            //Ne pas mettre de Toast dans des thread
                            //Toast.makeText(getContext(), "Connexion réussie", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), AnnonceActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getContext(), "Echec de connexion", Toast.LENGTH_SHORT).show();
                        }
                    }
            });
        });
    }
}