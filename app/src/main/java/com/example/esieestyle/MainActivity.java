package com.example.esieestyle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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
}

    //qui montre comment avec Firestore on peut lister, filtrer et noter en ligne des restaurants présentés dans une magnifique RecyclerView.