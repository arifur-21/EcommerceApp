package com.example.foodproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodproject.databinding.FragmentLoginBinding;
import com.example.foodproject.model.AddProductModel;
import com.example.foodproject.model.UsersModel;
import com.example.foodproject.viewModel.LoginViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginFragment extends Fragment {

   private FragmentLoginBinding binding;
   private LoginViewModel loginViewModel;
   private ProgressDialog lodingBar;
   private String parentDbName = "NewUsers";

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        binding = FragmentLoginBinding.inflate(inflater);
        lodingBar = new ProgressDialog(getContext());
        loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginViewModel.authenticationMutableLiveData.observe(getViewLifecycleOwner(), new Observer<LoginViewModel.Authentication>() {
            @Override
            public void onChanged(LoginViewModel.Authentication authentication) {
                if (authentication == LoginViewModel.Authentication.AUTHENTICATION)
                {
                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_nav_home);
                }
            }
        });
        binding.adminPenalLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.loginBtn.setText("Logmin Admin");
                binding.adminPenalLink.setVisibility(View.INVISIBLE);
                binding.notAdminPenalLink.setVisibility(View.VISIBLE);
                parentDbName = "Admins";
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_productCategory);

            }
        });
        binding.notAdminPenalLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.loginBtn.setText("Admin Panel");
                binding.notAdminPenalLink.setVisibility(View.INVISIBLE);
                binding.adminPenalLink.setVisibility(View.VISIBLE);
                parentDbName = "NewUsers";
            }
        });

        binding.registerId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* lodingBar.setTitle("Create Account");
                lodingBar.setMessage("Please wite While we checking Creadintial");
                lodingBar.setCanceledOnTouchOutside(false);
                lodingBar.show();*/

                final String email = binding.loginPhoneNumberInput.getText().toString();
                final String password = binding.loginPasswordInput.getText().toString();


                if (TextUtils.isEmpty(email))
                {
                    binding.loginPhoneNumberInput.setError("Enter your Email");
                }
                else   if (TextUtils.isEmpty(password))
                {
                    binding.loginPasswordInput.setError("Enter your Passwordd");
                }

                else {

                    loginViewModel.login(email, password);

                }
            }

        });



    }


}