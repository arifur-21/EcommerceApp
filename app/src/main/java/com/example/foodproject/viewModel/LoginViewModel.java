package com.example.foodproject.viewModel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends AndroidViewModel {

    private FirebaseAuth auth;
    public MutableLiveData<Authentication> authenticationMutableLiveData;
    public MutableLiveData<String> errorMsg;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        auth = FirebaseAuth.getInstance();
        authenticationMutableLiveData = new MutableLiveData<>();
        errorMsg = new MutableLiveData<>();

        if (auth.getCurrentUser()==null)
        {
            authenticationMutableLiveData.postValue(Authentication.UNAUTHENTICATION);
        }
        else{
                authenticationMutableLiveData.postValue(Authentication.AUTHENTICATION);
    }
    }
    public void login (String email, String password)
    {

        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                authenticationMutableLiveData.postValue(Authentication.AUTHENTICATION);
                Toast.makeText(getApplication(), "successfull", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                errorMsg.postValue(e.getLocalizedMessage());
                Toast.makeText(getApplication(), "Error :"+e, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void register (String email, String password, String name)
    {
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                authenticationMutableLiveData.postValue(Authentication.AUTHENTICATION);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                errorMsg.postValue(e.getLocalizedMessage());
            }
        });
    }

    public void logOut()
    {
        if (auth != null)
        {
            auth.signOut();
            authenticationMutableLiveData.postValue(Authentication.UNAUTHENTICATION);
        }
    }

    public enum Authentication{
        AUTHENTICATION, UNAUTHENTICATION
    }
}
