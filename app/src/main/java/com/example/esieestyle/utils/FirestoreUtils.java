package com.example.esieestyle.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreUtils {

    private static FirebaseAuth firebaseAuth;
    private static FirebaseFirestore firebaseFirestore;

    public static FirebaseAuth getFirebaseAuth(){
        if(firebaseAuth == null)
            firebaseAuth = FirebaseAuth.getInstance();
        return firebaseAuth;
    }

    public static void signOut(){
        getFirebaseAuth().signOut();
    }

    public static FirebaseFirestore getFirestore(){
        if (firebaseFirestore == null)
            firebaseFirestore = FirebaseFirestore.getInstance();
        return firebaseFirestore;
    }

    public static String getUserID(){
        FirebaseUser firebaseUser = getFirebaseAuth().getCurrentUser();
        if (firebaseUser != null)
            return firebaseUser.getUid();
        return null;
    }

    public static CollectionReference getCollectionRef(String collection){
        return getFirestore().collection(collection);
    }

    public static CollectionReference getSousCollectionRef(String collection, String sous_collection){
        return getFirestore().collection(collection).document(getUserID()).collection(sous_collection);
    }

    public static CollectionReference getAjoutUserCollectionRef(){
        return getCollectionRef("Users").document(getUserID()).collection("Annonces ajoutées");
    }
}
