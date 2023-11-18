package com.example.esieestyle;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

    private Fragment fragment = new HomeFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce);

        BottomNavigationView bottomNavigationView= findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
            }
        });
    }

    private void changeFragment()
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view_id_Annonce, fragment);
        fragmentTransaction.commit();
    }
}