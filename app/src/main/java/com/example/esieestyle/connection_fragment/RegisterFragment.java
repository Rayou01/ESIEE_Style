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
import com.example.esieestyle.databinding.FragmentRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterFragment extends Fragment {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private FragmentRegisterBinding binding;

    private boolean is_Name_Text_Empty, is_Surname_Text_Empty, is_Email_Text_Empty, is_Phone_Text_Empty, is_Password_Text_Empty = true;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.validateRegistrationButton.setEnabled(false);
        firebaseAuth.signOut();

        is_Name_Text_Empty = true;
        is_Surname_Text_Empty = true;
        is_Email_Text_Empty = true;
        is_Phone_Text_Empty = true;
        is_Password_Text_Empty = true;

        binding.registerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                is_Name_Text_Empty = editable.toString().isEmpty();
                binding.validateRegistrationButton.setEnabled(!(is_Surname_Text_Empty
                        || is_Name_Text_Empty || is_Email_Text_Empty || is_Phone_Text_Empty || is_Password_Text_Empty));
            }
        });

        binding.registerSurname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                is_Surname_Text_Empty = editable.toString().isEmpty();
                binding.validateRegistrationButton.setEnabled(!(is_Surname_Text_Empty
                        || is_Name_Text_Empty | is_Email_Text_Empty || is_Phone_Text_Empty || is_Password_Text_Empty));
            }
        });

        binding.mailEsiee.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                is_Email_Text_Empty = editable.toString().isEmpty();
                binding.validateRegistrationButton.setEnabled(!(is_Surname_Text_Empty ||
                        is_Name_Text_Empty || is_Email_Text_Empty || is_Phone_Text_Empty || is_Password_Text_Empty));
            }
        });

        binding.phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                is_Phone_Text_Empty = editable.toString().isEmpty();
                binding.validateRegistrationButton.setEnabled(!(is_Surname_Text_Empty
                        || is_Name_Text_Empty || is_Email_Text_Empty || is_Phone_Text_Empty || is_Password_Text_Empty));

            }
        });

        binding.registerPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                is_Password_Text_Empty = editable.toString().isEmpty();
                binding.validateRegistrationButton.setEnabled(!(is_Surname_Text_Empty
                        || is_Name_Text_Empty || is_Email_Text_Empty || is_Phone_Text_Empty || is_Password_Text_Empty));
            }
        });

        binding.registerToConnectionPageButton.setOnClickListener(view1 -> {
            //Naviguer vers le fragment précédent
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ConnectionFragment connectionFragment = new ConnectionFragment();
            fragmentTransaction.replace(R.id.fragment_container_view_id_Main, connectionFragment);
            fragmentTransaction.commit();
        });

        binding.validateRegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = String.valueOf(binding.mailEsiee.getText());
                password = String.valueOf(binding.registerPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getContext(), "Entrer un mail", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "Entrer un mot de passe", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Compte créé", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), AnnonceActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getContext(), "Echec de la création du compte", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }
        });
    }
}