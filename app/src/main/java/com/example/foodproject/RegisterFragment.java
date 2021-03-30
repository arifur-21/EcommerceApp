package com.example.foodproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodproject.databinding.FragmentRegisterBinding;
import com.example.foodproject.model.UsersModel;
import com.example.foodproject.viewModel.LoginViewModel;
import com.example.foodproject.viewModel.ViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterFragment extends Fragment {

    private LoginViewModel loginViewModell;
    private ViewModel viewModel;
    private FragmentRegisterBinding binding;
    private ProgressDialog  lodingBar;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        loginViewModell = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        binding = FragmentRegisterBinding.inflate(inflater);
        lodingBar = new ProgressDialog(getActivity());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = binding.registerUserName.getText().toString();
                final String email = binding.registerLoginPhoneNumberInput.getText().toString();
                final String password = binding.registerLoginPasswordInput.getText().toString();
               // GlobalVariable.name = name;

                if (TextUtils.isEmpty(name))
                {
                    binding.registerUserName.setError("Enter your Name");
                }
                else   if (TextUtils.isEmpty(email))
                {
                    binding.registerLoginPhoneNumberInput.setError("Enter your Phone Number");
                }
                else if (TextUtils.isEmpty(password))
                {
                    binding.registerLoginPasswordInput.setError("Enter your Password");
                }

                // Creating Check Creadintial Dialog Box
                else {

                    lodingBar.setTitle("Create Account");
                    lodingBar.setMessage("Please wite While we checking Creadintial");
                    lodingBar.setCanceledOnTouchOutside(false);
                    lodingBar.show();
                    //this method adding name,phone,passowrd and check Validity
                    loginViewModell.register(email, password,name);
                    Toast.makeText(getContext(), "Register successfull",Toast.LENGTH_LONG).show();
                    Navigation.findNavController(v).navigate(R.id.action_registerFragment_to_loginFragment);
                    UsersModel usersModel = new UsersModel(name,password,email,null);
                    viewModel.addNewUsers(usersModel);

                }
            }
        });

    }


}