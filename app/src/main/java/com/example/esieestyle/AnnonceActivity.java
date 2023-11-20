package com.example.esieestyle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.esieestyle.annonce_fragment.AddAnnonceFragment;
import com.example.esieestyle.annonce_fragment.BasketFragment;
import com.example.esieestyle.annonce_fragment.FavorisFragment;
import com.example.esieestyle.annonce_fragment.HomeFragment;
import com.example.esieestyle.annonce_fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class AnnonceActivity extends AppCompatActivity {

    private Fragment fragment = new HomeFragment();
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce);

        bottomNavigationView= findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int item_id = item.getItemId();

            if(item_id == R.id.home_menu) {
                fragment = new HomeFragment();
            }
            else if(item_id == R.id.favorite_menu) {
                fragment = new FavorisFragment();
            }
            else if(item_id == R.id.add_annonce_menu) {
                fragment = new AddAnnonceFragment();
                changeFragment();
                bottomNavigationView.setVisibility(View.GONE);
                return true;
            }
            else if(item_id == R.id.basket_menu) {
                fragment = new BasketFragment();
            }
            else if(item_id == R.id.user_avatar_menu) {
                fragment = new UserFragment();
            }
            changeFragment();
            return true;
        });
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_view_id_Annonce);
        if(!(currentFragment instanceof HomeFragment)){
            fragment = new HomeFragment();
            changeFragment();
        }
        else
            displayDialogWindows();
        bottomNavigationView.setVisibility(View.VISIBLE);
        bottomNavigationView.setSelectedItemId(R.id.home_menu);
    }

    private void changeFragment()
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view_id_Annonce, fragment);
        fragmentTransaction.commit();
    }

    private void displayDialogWindows(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Vous allez vous déconnecter");
        builder.setMessage("Voulez-vous vraiment vous déconnecter ?");
        builder.setPositiveButton("Déconnecter", (dialog, id) -> {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.signOut();
            Intent intent = new Intent(AnnonceActivity.this, ConnectionActivity.class);
            startActivity(intent);
        })
                .setNegativeButton("Annuler", (dialogInterface, i) -> {
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}