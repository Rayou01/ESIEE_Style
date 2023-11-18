package com.example.esieestyle.annonce_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.esieestyle.R;
import com.example.esieestyle.databinding.FragmentAddAnnonceBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddAnnonceFragment extends Fragment {

    private FragmentAddAnnonceBinding binding;
    private FirebaseFirestore firebaseFirestore;
    private BottomNavigationView bottomNavigationView;

    public AddAnnonceFragment() {
        // Required empty public constructor
    }

    public static AddAnnonceFragment newInstance() {
        AddAnnonceFragment fragment = new AddAnnonceFragment();
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
        binding = FragmentAddAnnonceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();

        //Initialise le spinner pour les catégories
        Spinner spinnerCategories = view.findViewById(R.id.categorie_objet_spinner);
        ArrayAdapter<CharSequence> spinnerCategoriesAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.categories, android.R.layout.simple_spinner_item);
        spinnerCategoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(spinnerCategoriesAdapter);

        //Initialise le spinner pour l'état de l'objet
        Spinner spinnerState = view.findViewById(R.id.etat_objet_spinner);
        ArrayAdapter<CharSequence> spinnerStateAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.etat_objet, android.R.layout.simple_spinner_item);
        spinnerStateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(spinnerStateAdapter);

        binding.closePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Naviguer vers le fragment Home
                goOnHomeFragment();
            }
        });

        binding.newDoneAnnonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleAnnonce = binding.titleAnnonceEditText.getEditText().getText().toString();
                String stateAnnonce = binding.etatObjetSpinner.getSelectedItem().toString();
                String priceAnnonce = binding.prixObjetInputText.getEditText().getText().toString();
                String infosAnnonce = binding.informationsContactTextView2.getText().toString();
                String descriptionAnnonce = binding.descriptionAnnonceInputText.getEditText().getText().toString();
                Calendar calendar = Calendar.getInstance();
                String dateAnnonce = DateFormat.getDateInstance().format(calendar.getTime());

                Map<String,Object> newAnnonce = new HashMap<>();
                newAnnonce.put("productName",titleAnnonce);
                newAnnonce.put("sellerName","Vendeurdufutur");
                newAnnonce.put("producPrice",priceAnnonce);
                newAnnonce.put("productState",stateAnnonce);
                //newAnnonce.put("annonceDate",dateAnnonce);
                //newAnnonce.put("annonceInfos",infosAnnonce);
                //newAnnonce.put("annonceDescription",descriptionAnnonce);

                firebaseFirestore.collection("Annonces")
                        .add(newAnnonce)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                goOnHomeFragment();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(requireContext(), "Echec de publication", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }

    private void goOnHomeFragment(){
        bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home_menu);
        bottomNavigationView.setVisibility(View.VISIBLE);
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.fragment_container_view_id_Annonce, homeFragment);
        fragmentTransaction.commit();
    }
}