package com.example.esieestyle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.example.esieestyle.databinding.FragmentConnectionBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
/*
Reference pour l'avancer effectue par Ryan :
https://openclassrooms.com/fr/courses/8150246-developpez-votre-premiere-application-android/8256305-accedez-aux-elements-interactifs-depuis-le-code-java-de-votre-fragment
 */

//Commit à chaque ajout de fonctionnalité ou réparation de bug

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }
}