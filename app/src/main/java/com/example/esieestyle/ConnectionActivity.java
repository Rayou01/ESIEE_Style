package com.example.esieestyle;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.esieestyle.utils.FirestoreUtils;
/*
Reference pour l'avancer effectue par Ryan :
https://openclassrooms.com/fr/courses/8150246-developpez-votre-premiere-application-android/8256305-accedez-aux-elements-interactifs-depuis-le-code-java-de-votre-fragment
 */

public class ConnectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                    FirestoreUtils.signOut();
        })
                .setNegativeButton("Annuler", (dialogInterface, i) -> {
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}