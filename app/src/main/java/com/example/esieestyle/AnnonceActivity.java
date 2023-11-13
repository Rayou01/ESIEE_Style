package com.example.esieestyle;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.esieestyle.annonce_fragment.AddAnnonceFragment;
import com.example.esieestyle.annonce_fragment.BasketFragment;
import com.example.esieestyle.annonce_fragment.FavorisFragment;
import com.example.esieestyle.annonce_fragment.HomeFragment;
import com.example.esieestyle.annonce_fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AnnonceActivity extends AppCompatActivity {
    private HomeFragment fragment = new HomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce);

        //Récupère la Toolbar et l'active sur l'activité
        Toolbar toolbar = findViewById(R.id.top_Toolbar);
        setSupportActionBar(toolbar);

        //Permet d'activer la toolbar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //On ajoute un listener sur la Toolbar
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int item_id = item.getItemId();

                if(item_id == R.id.home_menu) {
                    Fragment fragment = new HomeFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container_view_id_Annonce, fragment);
                    fragmentTransaction.commit();
                }
                else if(item_id == R.id.favorite_menu) {
                    Fragment fragment = new FavorisFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container_view_id_Annonce, fragment);
                    fragmentTransaction.commit();
                }
                else if(item_id == R.id.add_annonce_menu) {
                    Fragment fragment = new AddAnnonceFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container_view_id_Annonce, fragment);
                    fragmentTransaction.commit();
                }
                else if(item_id == R.id.basket_menu) {
                    Fragment fragment = new BasketFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container_view_id_Annonce, fragment);
                    fragmentTransaction.commit();
                }
                else if(item_id == R.id.user_avatar_menu) {
                    Fragment fragment = new UserFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container_view_id_Annonce, fragment);
                    fragmentTransaction.commit();
                }
                return true;
            }
        });
    }
}