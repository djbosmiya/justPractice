package com.example.hrminiapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hrminiapp.databinding.FragmentAddBinding;

public class AddFragment extends Fragment implements View.OnClickListener{

    FragmentAddBinding addBinding;
    DBHelper dbh;
    Boolean insertStatus;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        addBinding = FragmentAddBinding.inflate(inflater,container, false);
        View view = addBinding.getRoot();
        init();
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == addBinding.btnSubmit.getId()){
            Employee objEmp = CreateEmployee();
            insertStatus = dbh.InsertEmployee(objEmp);

            if(insertStatus){
                Toast.makeText(getActivity(), "Employee record added successfully", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getActivity(), "Record insertion failure", Toast.LENGTH_LONG).show();
            }
        }
    }

    public Employee CreateEmployee() {
        Employee objEmp1 = new Employee();
        objEmp1.setName(addBinding.edtName.getText().toString().trim());
        objEmp1.setDesig(addBinding.edtDesig.getText().toString().trim());
        objEmp1.setDept(addBinding.edtDept.getText().toString().trim());
        objEmp1.setEmailId(addBinding.edtEmail.getText().toString().trim());
        objEmp1.setSalary(Integer.parseInt(addBinding.edtSalary.getText().toString().trim()));
        return objEmp1;
    }

    private void init(){
        dbh = new DBHelper(getActivity());
        addBinding.btnSubmit.setOnClickListener(this);
    }
}