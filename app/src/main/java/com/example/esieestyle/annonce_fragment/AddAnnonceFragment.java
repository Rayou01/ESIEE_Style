package com.example.esieestyle.annonce_fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.esieestyle.R;
import com.example.esieestyle.databinding.FragmentAddAnnonceBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddAnnonceFragment extends Fragment {

    private FragmentAddAnnonceBinding binding;
    private FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

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
        ArrayAdapter<CharSequence> spinnerCategoriesAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.categories, android.R.layout.simple_spinner_item);
        spinnerCategoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.categorieObjetSpinner.setAdapter(spinnerCategoriesAdapter);

        //Initialise le spinner pour l'état de l'objet
        ArrayAdapter<CharSequence> spinnerStateAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.etat_objet, android.R.layout.simple_spinner_item);
        spinnerStateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.etatObjetSpinner.setAdapter(spinnerStateAdapter);

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
                //On récupère les données sous forme de String
                String titleAnnonce = Objects.requireNonNull(binding.titleAnnonceEditText.getEditText()).getText().toString();
                String stateAnnonce = binding.etatObjetSpinner.getSelectedItem().toString();
                String priceAnnonce = Objects.requireNonNull(binding.prixObjetInputText.getEditText()).getText().toString();
                //String infosAnnonce = binding.informationsContactTextView2.getText().toString();
                //String descriptionAnnonce = binding.descriptionAnnonceInputText.getEditText().getText().toString();

                //On vérifie si elles sont vides
                if (titleAnnonce.equals("") || priceAnnonce.equals("")){
                    displayDialogWindows();
                    return;
                }
                //On récupère la date du jour (sans l'heure) que l'on envoie sous forme de string
                Calendar calendar = Calendar.getInstance();
                String dateAnnonce = DateFormat.getDateInstance().format(calendar.getTime());

                //On retire le symbole € de la string et on récupère le float
                String[] priceString = priceAnnonce.split(" ");
                float priceProduct = Float.parseFloat(priceString[0]);

                //On ajoute les données de l'annonce
                Map<String, Object> newAnnonce = new HashMap<>();
                newAnnonce.put("productName", titleAnnonce);
                newAnnonce.put("sellerName", "Vendeurdufutur");
                newAnnonce.put("productPrice", priceProduct);
                newAnnonce.put("annonceDate", dateAnnonce);
                newAnnonce.put("productState", stateAnnonce);
                //newAnnonce.put("annonceInfos",infosAnnonce);
                //newAnnonce.put("annonceDescription",descriptionAnnonce);

                //Enfin, on poste l'annonce sur Firestore
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

    private void displayDialogWindows() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        builder.setTitle("Publication impossible");
        builder.setMessage("Veuillez remplir toutes les informations");
        builder.setPositiveButton("Compris", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void goOnHomeFragment(){
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home_menu);
        bottomNavigationView.setVisibility(View.VISIBLE);
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.fragment_container_view_id_Annonce, homeFragment);
        fragmentTransaction.commit();
    }
}