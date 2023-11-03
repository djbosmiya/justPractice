package com.example.hrminiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hrminiapp.databinding.ActivityLoginBinding;
import com.example.hrminiapp.models.User;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityLoginBinding loginBinding;
    Intent intent2;
    SharedPreferences sharedPref1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = loginBinding.getRoot();
        setContentView(view);
        init();
    }

    public void onClick(View v){
        if(v.getId() == loginBinding.btnLogin.getId()){
            if(loginBinding.edtUserId.getText().toString().trim().equals("dhara") && loginBinding.edtPasswd.getText().toString().trim().equals("test")){
//                intent2 = new Intent(this, TestHomeActivity.class);
                intent2 = new Intent(this, HomeActivity.class);
                //intent2.putExtra("USER_ID", loginBinding.edtUserId.getText().toString().trim());
                //User objUser = new User(loginBinding.edtUserId.getText().toString().trim(), loginBinding.edtEmailid.getText().toString().trim());
                //intent2.putExtra("USER_DETAILS", objUser);

                SharedPreferences.Editor editor1 = sharedPref1.edit();
                editor1.putString("USER_ID", loginBinding.edtUserId.getText().toString().trim());
                editor1.putString("EMAIL_ID", loginBinding.edtEmailid.getText().toString().trim());
                editor1.commit();
                startActivity(intent2);

            }
            else {
//                Toast.makeText(getApplicationContext(), "Invalid USer Id or Password", Toast.LENGTH_LONG).show();

//                LoginFailDialog dialog1 = new LoginFailDialog();
//                dialog1.show(getSupportFragmentManager(), "Invalid");

                new MaterialAlertDialogBuilder(LoginActivity.this)
                        .setTitle("Login Failed")
                        .setMessage("Invalid UserId and/or Password")
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(getActivity(), "Positive Button Clicked", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(getActivity(),"Negative Button Clicked", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        }
    }

    private void init(){
        loginBinding.btnLogin.setOnClickListener(this);
        sharedPref1 = getSharedPreferences("login_details", Context.MODE_PRIVATE);
    }
}