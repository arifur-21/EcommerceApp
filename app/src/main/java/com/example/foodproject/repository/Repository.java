package com.example.foodproject.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.foodproject.CustomarInformation;
import com.example.foodproject.GlobalVariable;
import com.example.foodproject.model.AddProductModel;
import com.example.foodproject.model.CustomarInfo;
import com.example.foodproject.model.RatingModel;
import com.example.foodproject.model.UsersModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private FirebaseFirestore db;
    private String COLLECTION_ADDPRODUCT = "AddProduct";
    private String COLLECTION_NEWUSERS = "NewUsers";
    private String COLLECTION_CUSTOMAR = "CustomarInformation";
    private String COLLECTION_RATING = "Rating";

    public Repository() {
        db = FirebaseFirestore.getInstance();
    }

    public void AddProduct(AddProductModel addProductModel){
        final DocumentReference docRef = db.collection(COLLECTION_ADDPRODUCT).document();
        addProductModel.setId(docRef.getId());
        docRef.set(addProductModel).addOnCompleteListener(task -> {
            if (task.isSuccessful())
            {
                Log.e("tag", "Save Product");
            }
        }).addOnFailureListener(e -> Log.e("tag", e.getLocalizedMessage()));
    }

    public void NewUsers(UsersModel usersModel){
        final DocumentReference docRef = db.collection(COLLECTION_NEWUSERS).document();
        usersModel.setUserId(docRef.getId());
        docRef.set(usersModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    public void addRating (RatingModel ratingModel)
    {
        DocumentReference docRef = db.collection(COLLECTION_RATING).document();
        ratingModel.setRating(docRef.getId());
        docRef.set(ratingModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    public void addCustomarInformation(CustomarInfo customarInfo)
    {
        final DocumentReference docRef = db.collection(COLLECTION_CUSTOMAR).document();
        customarInfo.setCustomarId(docRef.getId());

        docRef.set(customarInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    public MutableLiveData<List<AddProductModel>> getAllProduct (){
        MutableLiveData<List<AddProductModel>> modelMutableLiveData = new MutableLiveData<>();
        db.collection(COLLECTION_ADDPRODUCT).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null)
                {
                    return;
                }

                List<AddProductModel>list = new ArrayList<>();
                for (DocumentSnapshot snapshot : value.getDocuments()){
                    AddProductModel addProductModel = snapshot.toObject(AddProductModel.class);
                    list.add(addProductModel);
                    modelMutableLiveData.postValue(list);

                }
            }
        });

        return modelMutableLiveData;
    }
}
