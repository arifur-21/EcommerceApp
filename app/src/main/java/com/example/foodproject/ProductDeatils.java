package com.example.foodproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodproject.databinding.FragmentProductDeatilsBinding;
import com.squareup.picasso.Picasso;


public class ProductDeatils extends Fragment {

    private FragmentProductDeatilsBinding binding;
    private String name,price,image;


    public ProductDeatils() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductDeatilsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        name = bundle.getString("name");
        price = bundle.getString("price");
        image = bundle.getString("image");
       Picasso.get().load(image).into(binding.detailsImageViewId);
       binding.detailsNameid.setText(name);
       binding.detailsPriceId.setText("$ "+price);

       binding.buyId.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Navigation.findNavController(v).navigate(R.id.action_productDeatils_to_customarInformation);

           }
       });


    }
}