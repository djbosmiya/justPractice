package com.example.hrminiapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hrminiapp.databinding.FragmentDeleteBinding;

public class DeleteFragment extends Fragment implements View.OnClickListener{

    FragmentDeleteBinding deleteBinding;
    DBHelper dbh;

    public DeleteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        deleteBinding = FragmentDeleteBinding.inflate(inflater,container, false);
        View view = deleteBinding.getRoot();
        init();
        return view;
    }

    private void init(){
        dbh = new DBHelper(getActivity());
        deleteBinding.btnDelete.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v.getId() == deleteBinding.btnDelete.getId()){
            int deletedRows = dbh.DeleteEmployee(Integer.parseInt(deleteBinding.edtEmpId.getText().toString().trim()));
            if(deletedRows > 0){
                Toast.makeText(getActivity(), "Employee record deleted successfully", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getActivity(), "Record deletion failure", Toast.LENGTH_LONG).show();
            }
        }
    }
}