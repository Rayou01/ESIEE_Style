package com.example.esieestyle;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.LogPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.esieestyle.databinding.FragmentConnectionBinding;

public class ConnectionFragment extends Fragment {

    private FragmentConnectionBinding binding;

    public boolean is_User_Text_Empty, is_Password_Text_Empty;

    public static ConnectionFragment newInstance() {
        ConnectionFragment fragment = new ConnectionFragment();
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
        binding = FragmentConnectionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //When the UI is created, we disable the connection button until the user enter something
        binding.connectionButton.setEnabled(false);

        binding.userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.connectionButton.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //We look if the user has enter a text for the user name EditText
                is_User_Text_Empty = editable.toString().isEmpty();
            }
        });

        binding.userPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.connectionButton.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //We look if the user has enter a text for the user password EditText
                is_Password_Text_Empty = editable.toString().isEmpty();
                //Then, we tes if both of EditText has been modified and enable the button if it's the case
                binding.connectionButton.setEnabled(!(is_Password_Text_Empty||is_User_Text_Empty));
            }
        });

        binding.connectionButton.setOnClickListener(view1 -> {
            //Naviguer vers le fragment suivant
        });
    }
}