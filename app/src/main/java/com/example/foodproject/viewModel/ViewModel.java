package com.example.foodproject.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.foodproject.model.AddProductModel;
import com.example.foodproject.model.CustomarInfo;
import com.example.foodproject.model.RatingModel;
import com.example.foodproject.model.UsersModel;
import com.example.foodproject.repository.Repository;

import java.util.List;

public class ViewModel  extends AndroidViewModel {

    private Repository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    //add porduct
    public void addNewProduct(AddProductModel addProductModel){
        repository.AddProduct(addProductModel);
    }
    //add Customar
    public void AddCustomar(CustomarInfo customarInfo)
    {
        repository.addCustomarInformation(customarInfo);
    }

    public void addRating(RatingModel ratingModel)
    {
        repository.addRating(ratingModel);
    }
    public void addNewUsers(UsersModel usersModel)
    {
        repository.NewUsers(usersModel);
    }

    // get product

    public MutableLiveData<List<AddProductModel>> feachAllProduct()
    {
        return repository.getAllProduct();
    }
}
