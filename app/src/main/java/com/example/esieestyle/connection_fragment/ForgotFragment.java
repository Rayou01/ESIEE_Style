package com.example.esieestyle.connection_fragment;

import android.os.Bundle;
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

import com.example.esieestyle.R;
import com.example.esieestyle.databinding.FragmentForgotBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotFragment extends Fragment {

    private boolean is_User_Text_Empty = true;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FragmentForgotBinding binding;

    public static ForgotFragment newInstance() {
        ForgotFragment fragment = new ForgotFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentForgotBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //When the UI is created, we disable the connection button until the user enter something
        binding.resetPasswordButton.setEnabled(false);
        firebaseAuth.signOut();
        //Toast.makeText(getContext(), "Veuillez vous connecter", Toast.LENGTH_LONG).show();

        is_User_Text_Empty = true;

        binding.userEmailReset.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //We look if the user has entered text for the user name EditText
                is_User_Text_Empty = editable.toString().isEmpty();
                binding.resetPasswordButton.setEnabled(!(is_User_Text_Empty));
            }
        });

        // This part should be inside onViewCreated
        binding.forgotToConnectionPageButton.setOnClickListener(view1 -> {
            //Naviguer vers le fragment connection
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ConnectionFragment connectionFragment = new ConnectionFragment();
            fragmentTransaction.replace(R.id.fragment_container_view_id_Main, connectionFragment);
            fragmentTransaction.commit();
        });
    }
}
