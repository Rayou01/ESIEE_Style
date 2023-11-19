package com.example.esieestyle.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.esieestyle.model.Annonce;

public class AnnonceViewModel extends ViewModel {
    private MutableLiveData<Annonce> annonceMutableLiveData = new MutableLiveData<>();

    public void addAnnonce(Annonce annonce){
        annonceMutableLiveData.setValue(annonce);
    }

    public LiveData<Annonce> getAnonce(){
        return annonceMutableLiveData;
    }

}
