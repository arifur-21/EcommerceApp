package com.example.foodproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.foodproject.databinding.FragmentCustomarInformationBinding;
import com.example.foodproject.model.CustomarInfo;
import com.example.foodproject.viewModel.ViewModel;

public class CustomarInformation extends Fragment {

    private FragmentCustomarInformationBinding binding;
    private final String cityes [] = {"Dhaka","Feni","Cumilla"};
    private String city;
    private String gender = "Male";
    private ViewModel viewModel;

    public CustomarInformation() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCustomarInformationBinding.inflate(inflater);

        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item,cityes);
        binding.cityId.setAdapter(arrayAdapter);

        binding.customarInformationSaveId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.customarNameId.getText().toString();
                String phone = binding.customarPhoneId.getText().toString();
                String address = binding.customarAddressId.getText().toString();

                CustomarInfo customarInfo = new CustomarInfo(name,phone,address,gender,city,null);
                viewModel.AddCustomar(customarInfo);
                Toast.makeText(getContext(),"Save Successfull",Toast.LENGTH_LONG).show();

            }
        });

        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = new RadioButton(getActivity());
                gender = button.getText().toString();
            }
        });

        binding.cityId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = cityes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.fabId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_customarInformation_to_cardFragment);
            }
        });
    }
}