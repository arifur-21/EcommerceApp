package com.example.foodproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodproject.databinding.FragmentProductCategoryBinding;

public class ProductCategory extends Fragment {

    private FragmentProductCategoryBinding binding;

    public ProductCategory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProductCategoryBinding.inflate(inflater);
        return  binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.shoesId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("category","Shoes");
                Navigation.findNavController(v).navigate(R.id.action_productCategory_to_addNewProducg,bundle);
            }
        });
        binding.clothId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("category","Cloth");
                Navigation.findNavController(v).navigate(R.id.action_productCategory_to_addNewProducg,bundle);
            }
        });

        binding.glassId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("category","Glass");
                Navigation.findNavController(v).navigate(R.id.action_productCategory_to_addNewProducg,bundle);
            }
        });
        binding.CameraId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("category","Camera");
                Navigation.findNavController(v).navigate(R.id.action_productCategory_to_addNewProducg,bundle);
            }
        });
    }
}