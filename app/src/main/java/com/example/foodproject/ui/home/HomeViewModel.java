package com.example.foodproject.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodproject.repository.Repository;

public class HomeViewModel extends AndroidViewModel {

    private Repository repository;
    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    
}