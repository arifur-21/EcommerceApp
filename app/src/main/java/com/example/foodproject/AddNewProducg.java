package com.example.foodproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodproject.databinding.FragmentAddNewProducgBinding;
import com.example.foodproject.model.AddProductModel;
import com.example.foodproject.viewModel.ViewModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

public class AddNewProducg extends Fragment {


    private FragmentAddNewProducgBinding binding;
    private ViewModel viewModel;

    private String categoryKey,description,Pprice, PName,savCurrentDate, saveCurrentTime;
    private static final int galleryPick = 1;
    private Uri imageUri;
    private String productRendomKey;
    private StorageReference produtcImageRef;
    private String downloadUri;

    private ProgressDialog lodingBar;
    public AddNewProducg() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        bundle.getString("catergory");

        binding = FragmentAddNewProducgBinding.inflate(inflater);
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        lodingBar = new ProgressDialog(getActivity());
        produtcImageRef = FirebaseStorage.getInstance().getReference().child("product image");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.selectProductImage.setOnClickListener(v -> openGallery());

        binding.productAddButton.setOnClickListener(v -> {
            ValidateProductData();


        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,galleryPick);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == galleryPick && resultCode == RESULT_OK && data != null)
        {
            imageUri = data.getData();
            binding.selectProductImage.setImageURI(imageUri);
        }
    }

    private void ValidateProductData() {

        PName = binding.productName.getText().toString();
        description = binding.productDescription.getText().toString();
        Pprice = binding.productPrice.getText().toString();

        if (imageUri == null)
        { Toast.makeText(getActivity(), "product image is mandatory..",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(PName))
        {
            binding.productName.setError("Enter your Product Name");
        }
        else if (TextUtils.isEmpty(description))
        {
            binding.productDescription.setError("Enter your Product Description");
        }
        else if (TextUtils.isEmpty(Pprice))
        {
            binding.productPrice.setError("Enter your Product Price");
        }
        else {
            storeProductInformation();
        }
    }

    private void storeProductInformation() {

        lodingBar.setTitle("Adding product");
        lodingBar.setMessage("Dear Admin please wite we are adding new product..");
        lodingBar.setCanceledOnTouchOutside(false);
        lodingBar.show();


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        savCurrentDate = currentDate.format(calendar.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRendomKey = savCurrentDate + saveCurrentTime;

        StorageReference filePath = produtcImageRef.child(imageUri.getLastPathSegment() + productRendomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(imageUri);

        uploadTask.addOnFailureListener(e -> {
            final String message = e.toString();
            Toast.makeText(getActivity(), "Storage Error :"+message,Toast.LENGTH_SHORT).show();
            lodingBar.dismiss();
        }).addOnSuccessListener(taskSnapshot -> {
            Task<Uri> uriTask = uploadTask.continueWithTask(task -> {
                if (!task.isSuccessful())
                {
                    throw task.getException();
                }
                downloadUri = filePath.getDownloadUrl().toString();
                return filePath.getDownloadUrl();

            }).addOnCompleteListener(task -> {
                if (task.isSuccessful())
                {
                    downloadUri = task.getResult().toString();
                    Toast.makeText(getActivity(), "got the product url successfull...",Toast.LENGTH_SHORT).show();
                    AddProductModel productModel = new AddProductModel(PName,Pprice,description,downloadUri,null,savCurrentDate, saveCurrentTime);
                    viewModel.addNewProduct(productModel);
                    lodingBar.dismiss();
                }
            });
        });
    }
}