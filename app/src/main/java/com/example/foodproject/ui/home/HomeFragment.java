package com.example.foodproject.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodproject.GlobalVariable;
import com.example.foodproject.R;
import com.example.foodproject.model.RatingModel;
import com.example.foodproject.viewModel.LoginViewModel;
import com.example.foodproject.viewModel.ViewModel;
import com.example.foodproject.adapter.ShowProductAdapter;
import com.example.foodproject.adapter.SliderAdapter;
import com.example.foodproject.databinding.FragmentHomeBinding;
import com.example.foodproject.model.AddProductModel;
import com.google.firebase.auth.FirebaseAuth;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.List;

public class HomeFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private FragmentHomeBinding binding;
    private ViewModel viewModel;
    private SliderAdapter adapter;
    private int[] images;
    private String[] text;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        viewModel = new ViewModelProvider(requireActivity()).get(ViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater);

        //slider
        images = new int[] {R.drawable.slider1,R.drawable.slider2, R.drawable.slider3};
        text = new String[]{"First Image", "Second Image", "Third Image"};
        adapter = new SliderAdapter(images,text);
        binding.sliderViewId.setSliderAdapter(adapter);
        binding.sliderViewId.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        binding.sliderViewId.setIndicatorAnimation(IndicatorAnimationType.DROP);
        binding.sliderViewId.startAutoCycle();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        loginViewModel.authenticationMutableLiveData.observe(getViewLifecycleOwner(), new Observer<LoginViewModel.Authentication>() {
            @Override
            public void onChanged(LoginViewModel.Authentication authentication) {
                if (authentication == LoginViewModel.Authentication.UNAUTHENTICATION)
                {
                    Navigation.findNavController(view).navigate(R.id.action_nav_home_to_loginFragment2);
                }
            }
        });


        viewModel.feachAllProduct().observe(getViewLifecycleOwner(), new Observer<List<AddProductModel>>() {
            @Override
            public void onChanged(List<AddProductModel> addProductModels) {
               final ShowProductAdapter adapter = new ShowProductAdapter(addProductModels, getActivity());
               final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
               binding.recycleViewId.setAdapter(adapter);
               binding.recycleViewId.setLayoutManager(layoutManager);

            }
        });
    }
}