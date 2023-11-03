package com.example.hrminiapp;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hrminiapp.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    FragmentProfileBinding profileBinding;
    Intent cameraIntent;

    public ProfileFragment() {
        // Required empty public constructor
    }

    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result!=null && result.getResultCode() == RESULT_OK){
                if(result.getData() != null){
                    Bundle extras = result.getData().getExtras();
                    Bitmap photo = (Bitmap)extras.get("data");
                    profileBinding.imgProfilePic.setImageBitmap(photo);
                }
            }
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile, container, false);
        profileBinding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = profileBinding.getRoot();
        init();
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == profileBinding.imgProfilePic.getId()){
            checkPermissionAndOpenCamera();
        }
    }

    private void checkPermissionAndOpenCamera() {
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, 5);
        }
        else {
            captureImage();
        }
    }

    private void captureImage() {
        cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startForResult.launch(cameraIntent);
    }

    private void init(){

    }
}