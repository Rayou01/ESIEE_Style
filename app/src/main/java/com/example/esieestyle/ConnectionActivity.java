package com.example.esieestyle;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
/*
Reference pour l'avancer effectue par Ryan :
https://openclassrooms.com/fr/courses/8150246-developpez-votre-premiere-application-android/8256305-accedez-aux-elements-interactifs-depuis-le-code-java-de-votre-fragment
 */

public class ConnectionActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null)
            startActivity(new Intent(ConnectionActivity.this, AnnonceActivity.class));
    }

    @Override
    public void onBackPressed() {
        displayDialogWindows();
    }

    private void displayDialogWindows(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Vous allez quitter l'application");
        builder.setMessage("Continuer ?");
        builder.setPositiveButton("Quitter", (dialogInterface, i) -> {
            finishAffinity();
            firebaseAuth.signOut();
        })
                .setNegativeButton("Annuler", (dialogInterface, i) -> {
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}